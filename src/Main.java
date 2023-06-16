import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        SingleLinkList<Tank> tanksList = new SingleLinkList<>();

        tanksList.addToEnd(new Tank(112, "T-34-85 ", "85 мм"));
        tanksList.addToEnd(new Tank(155, "Т-55", "100 мм"));
        tanksList.addToEnd(new Tank(184, "Т-72БА", "125 мм"));
        tanksList.addToEnd(new Tank(219, "Т-80", "125 мм"));
        tanksList.addToEnd(new Tank(188, "Т-90С", "125 мм"));

        System.out.println("-----------------------------------------------------------------------------------");
        for (Tank tank : tanksList) {
            System.out.println(tank);
        }

        tanksList.reverse();

        System.out.println("-----------------------------------------------------------------------------------");

        for (Tank tank : tanksList) {
            System.out.println(tank);
        }
    }
    static class Tank {
        int index;
        String model;
        String gun;

        public Tank(int index, String model, String gun) {
            this.index = index;
            this.model = model;
            this.gun = gun;
        }

        @Override
        public String toString() {
            return " Main battle tank - " + "Object index= " + index +
                    ", Tank model = " + model  + ", Gun caliber = " + gun  + " ;";
        }
    }

    public static class SingleLinkList<T> implements Iterable<T> {
        ListItem<T> head;
        ListItem<T> tail;

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void addToEnd(T item) {
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;
            if (isEmpty()) {
                head = newItem;
                tail = newItem;
            } else {
                tail.next = newItem;
                tail = newItem;
            }
        }

        public void reverse() {
            if (!isEmpty() && head.next != null) {
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}