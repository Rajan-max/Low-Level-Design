package DesignPatternPractice;

/*
     abstract class or interface
    *************************         has-a                    ************************
    *   Base Pizza         *  <-----------------------------   *   abstract decorator  *
    *                      *                                   *        class          *
    ************************   _________impl___________________*************************
           |                                                               |\
          |                                                               |   \
         |                                                               |      \
   **************************                                          |          \
   *  concrete              *                            concrete decorator1    concrete decorator2
   *  Base impl            *
   *************************

* */

interface BasePizza {
    int cost();

    String description();
}

interface PizzaDecorator extends BasePizza {
}

class Margherita implements BasePizza {

    @Override
    public int cost() {
        return 100;
    }

    @Override
    public String description() {
        return "Margherita Pizza ";
    }
}

class VegDelight implements BasePizza {

    @Override
    public int cost() {
        return 120;
    }

    @Override
    public String description() {
        return "Veg Delight ";
    }
}

class ExtraCheese implements PizzaDecorator {

    BasePizza basePizza;

    public ExtraCheese(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    public int cost() {
        return basePizza.cost() + 20;
    }

    public String description() {
        return basePizza.description() + "With Extra Cheese ";
    }

}

class ExtraMushroom implements PizzaDecorator {

    BasePizza basePizza;

    public ExtraMushroom(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    public int cost() {
        return basePizza.cost() + 30;
    }

    public String description() {
        return basePizza.description() + "With Extra Mushroom ";
    }

}

public class PizzaOrderingUsingDecorator {
    public static void main(String[] args) {
        BasePizza margherita = new Margherita();
        BasePizza extraCheese = new ExtraCheese(margherita);
        BasePizza extraMushroom = new ExtraMushroom(extraCheese);

        System.out.println(extraMushroom.cost() + " " + extraMushroom.description());
    }
}
