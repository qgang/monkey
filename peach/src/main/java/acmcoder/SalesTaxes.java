package acmcoder;

import java.util.*;

/**
 * Created by gang.qin on 2015/10/15.
 PROBLEM: SALES TAXES
 Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical
 products that are exempt. Import duty is an additional sales tax applicable on all imported
 goods at a rate of 5%, with no exemptions.
 When I purchase items I receive a receipt which lists the name of all the items and their price
 (including tax), finishing with the total cost of the items, and the total amounts of sales taxes
 paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains
 (np/100 rounded up to the nearest 0.05) amount of sales tax.
 Write an application that prints out the receipt details for these shopping baskets...
 INPUT
 Input 1:
 1 book at 12.49
 1 music CD at 14.99
 1 chocolate bar at 0.85
 Input 2:
 1 imported box of chocolates at 10.00
 1 imported bottle of perfume at 47.50
 Input 3:
 1 imported bottle of perfume at 27.99
 1 bottle of perfume at 18.99
 1 packet of headache pills at 9.75
 1 box of imported chocolates at 11.25
 OUTPUT
 Output 1:
 1 book: 12.49
 1 music CD: 16.49
 1 chocolate bar: 0.85
 Sales Taxes: 1.50
 Total: 29.83
 Output 2:
 1 imported box of chocolates:10.50
 1 imported bottle of perfume: 54.65
 Sales Taxes: 7.65
 Total: 65.15
 Output 3:
 1 imported bottle of perfume: 32.19
 1 bottle of perfume: 20.89
 1 packet of headache pills: 9.75
 1 box of imported chocolates: 11.85
 Sales Taxes: 6.70
 Total: 74.68
 */
public class SalesTaxes {

    private void excute() {
        List<Good> goods = getGoods();
        printGoods(goods);
    }

    //通过输入信息生成商品列表
    private List<Good> getGoods() {
        Scanner scan = new Scanner(System.in);
        String line = null;
        List<Good> goods = new ArrayList<Good>();
        while (scan.hasNext()) {
            line = scan.nextLine();
            Good good = buildGoodInfo(line);
            if (good != null) {
                goods.add(good);
            }
        }
        return goods;
    }

    //建立商品信息
    private Good buildGoodInfo(String line) {
        try {
            String[] strs = line.split(" ");

            int num = Integer.parseInt(strs[0]);
            String name = parseName(strs);
            boolean isExempt = isExempt(name);
            boolean isImported = name.contains(Code.IMPORTED);
            float preTaxPrice = Float.parseFloat(strs[strs.length - 1]);
            float tax = countTax(isExempt, isImported, preTaxPrice);
            float afterTaxPrice = preTaxPrice + tax;

            return new Good(num, name,isExempt, isImported, preTaxPrice, tax, afterTaxPrice);
        } catch (Exception e) {
            return null; //如果某条商品是信息输入的格式不正确，则不能建立这条商品的信息
        }
    }

    //构建商品名称
    private String parseName(String[] strs) {
        String name = "";
        for (int i = 1; i < strs.length; i++) {
            if (Code.AT.equals(strs[i])) {
                break; // at后面跟的数字应该为商品价格
            } else {
                name += strs[i] + " ";
            }
        }
        name = name.trim(); //去掉名字最后面的空格
        return name;
    }

    //判断商品是否诶免税产品
    private boolean isExempt(String name) {
        return  FreeTax.isFree(name);
    }

    //计算商品税费
    private float countTax(boolean isEempt, boolean isImported, float preTaxPrice) {
        float tax = 0;
        tax += isEempt ? 0 : Math.round(preTaxPrice * Code.RATE_TEN * 10) / 10.00;
        tax += isImported ? Math.round(preTaxPrice * Code.RATE_FIVE * 10) / 10.00 : 0;
        return tax;
    }

    //输出商品列表
    private void printGoods(List<Good> goods) {
        if (goods == null || goods.size() < 1) {
            return;
        }

        float taxes = 0;
        float total = 0;
        String outLine = "";
        for (Good good : goods) {
            outLine = good.getNum() + " " + good.getName() + ": " + good.getAfterTaxPrice();
            System.out.println(outLine);
            taxes += good.getTaxes();
            total += good.getAfterTaxPrice();
        }
        System.out.println(Code.WORD_SALES_TAXES + ": " + taxes);
        System.out.println(Code.WORD_TOTAL + ": " + total);
    }

    public static void main(String[] args) {
        SalesTaxes salesTaxes = new SalesTaxes();
        salesTaxes.excute();
    }

}

/**
 * 免税产品 （怎么判断是否为免税产品是个难题）
 */
class FreeTax {

    // 判断商品是否为免税产品
    public static boolean isFree(String name) {
        //TODO
        return false;
    }
}

/**
 * 常量信息，可以写到配置文件中去
 */
class Code {
    static final String AT = "at";                      // 价格标志
    static final String IMPORTED = "imported";          // 进口关键字
    static final float  RATE_TEN = 0.1f;                // 10%税率
    static final float  RATE_FIVE = 0.05f;              // 5%税率
    static final String WORD_SALES_TAXES="Sales Taxes"; // 文案“Sales Taxes”
    static final String WORD_TOTAL = "Total";           // 文案“Total”
}

/**
 * 商品信息类
 */
class Good {
    private int num;              //商品数量
    private String name;          //商品名称
    private boolean isExempt;     //商品是否免税（true:免税,false:不免税,默认是false）
    private boolean isImported;   //商品是否进口（true:进口,false:非进口,默认是false）
    private float preTaxPrice;    //商品税前价格
    private float taxes;          //商品税
    private float afterTaxPrice;  //商品税后价格

    public Good() {}

    public Good(int num, String name, boolean isExempt, boolean isImported, float preTaxPrice, float taxes, float afterTaxPrice) {
        this.num = num;
        this.name = name;
        this.isExempt = isExempt;
        this.isImported = isImported;
        this.preTaxPrice = preTaxPrice;
        this.taxes = taxes;
        this.afterTaxPrice = afterTaxPrice;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExempt() {
        return isExempt;
    }

    public void setIsExempt(boolean isExempt) {
        this.isExempt = isExempt;
    }

    public boolean isImported() {
        return isImported;
    }

    public void setIsImported(boolean isImported) {
        this.isImported = isImported;
    }

    public float getPreTaxPrice() {
        return preTaxPrice;
    }

    public void setPreTaxPrice(float preTaxPrice) {
        this.preTaxPrice = preTaxPrice;
    }

    public float getTaxes() {
        return taxes;
    }

    public void setTaxes(float taxes) {
        this.taxes = taxes;
    }

    public float getAfterTaxPrice() {
        return afterTaxPrice;
    }

    public void setAfterTaxPrice(float afterTaxPrice) {
        this.afterTaxPrice = afterTaxPrice;
    }
}
