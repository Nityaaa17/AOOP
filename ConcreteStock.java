import java.util.ArrayList;
import java.util.List;

interface Investor {
    void update(String stockName, double stockPrice);
}
interface Stock {
    void registerInvestor(Investor investor);
    void removeInvestor(Investor investor);
    void notifyInvestors();
}
class ConcreteStock implements Stock {
    private List<Investor> investors = new ArrayList<>();
    private String stockName;
    private double stockPrice;

    public ConcreteStock(String stockName, double stockPrice) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
    }
    @Override
    public void registerInvestor(Investor investor) {
        investors.add(investor);
    }
    @Override
    public void removeInvestor(Investor investor) {
        investors.remove(investor);
    }
       @Override
    public void notifyInvestors() {
        for (Investor investor : investors) {
            investor.update(stockName, stockPrice);
        }
    }
    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyInvestors();
    }
    public double getStockPrice() {
        return stockPrice;
    }

    public String getStockName() {
        return stockName;
    }
}
class ConcreteInvestor implements Investor {
    private String investorName;

    public ConcreteInvestor(String investorName) {
        this.investorName = investorName;
    }
    @Override
    public void update(String stockName, double stockPrice) {
        System.out.println("Investor " + investorName + " notified. " +
                "Stock: " + stockName + " is now $" + stockPrice);
    }

    public String getInvestorName() {
        return investorName;
    }
}
public class StockMarketSystem {
    public static void main(String[] args) {
        ConcreteStock appleStock = new ConcreteStock("Apple", 150.0);
        ConcreteStock googleStock = new ConcreteStock("Google", 2800.0);

        Investor investor1 = new ConcreteInvestor("John Doe");
        Investor investor2 = new ConcreteInvestor("Jane Smith");

        appleStock.registerInvestor(investor1);
        googleStock.registerInvestor(investor2);

                appleStock.setStockPrice(155.0);
        googleStock.setStockPrice(2850.0); 
        

        appleStock.registerInvestor(investor2);
        appleStock.setStockPrice(160.0);  
    }
}
