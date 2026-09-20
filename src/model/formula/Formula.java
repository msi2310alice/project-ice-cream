package model.formula;

import java.util.ArrayList;
import java.util.List;

public class Formula {
    private String idFormula;
    private String nameFormula;
    private List<FormulaItem> itemsFormula;

    public Formula(String idFormula, String nameFormula) {
        this.idFormula = idFormula;
        this.nameFormula = nameFormula;
        this.itemsFormula = new ArrayList<>();
    }

    public String getIdFormula() {
        return idFormula;
    }
    public String getNameFormula() {
        return nameFormula;
    }
    public List<FormulaItem> getItemsFormula() {
        return itemsFormula;
    }

    public void setNameFormula(String nameFormula) {
        this.nameFormula = nameFormula;
    }

    public void addItem(FormulaItem item) {
        itemsFormula.add(item);
    }
    public void removeItem(FormulaItem item) {
        itemsFormula.remove(item);
    }
    public double getTotalAmount() {
        double total = 0;
        for (FormulaItem item : itemsFormula) {
            total += item.getAmount();
        }
        return total;
    }

    @Override 
    public String toString() {
        return "formula{ " 
                + "idFormula=" + idFormula
                + ", nameFormula=" + nameFormula
                + ", totalAmount= " + getTotalAmount()
                + ", items= " + itemsFormula
                + "}";
    }
}
