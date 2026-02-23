public class Order {
    int num;
    String food;
    int table;

    public Order(int num, String food, int table) {
        this.num = num;
        this.food = food;
        this.table = table;
    }

    public String toString() {
        return num + " " + food + " table " + table;
    }
}