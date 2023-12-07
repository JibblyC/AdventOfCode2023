package Day7;

public class CardsDrawn implements Comparable {

//    private static String characterOrder = "23456789TJQKA";
    private static String characterOrder = "J23456789TQKA";

    private String draw;
    private long bid;
    private CamelHand camelHand;



    public CardsDrawn(String draw, long bid) {
        this.draw = draw;
        this.bid = bid;

    }

    @Override
    public int compareTo(Object o) {

        CardsDrawn handToCompare = (CardsDrawn) o;
        //Compare Individual Characters

        int result = Integer.compare(this.getCamelHand().getValue(), handToCompare.getCamelHand().getValue());

        if(result > 0){
            return 1;
        }else if(result < 0){
            return -1;
        }else{
            for (int i = 0; i < draw.length(); i++) {
                int myValue = getCharacterStrength(draw.charAt(i));
                int otherValue = getCharacterStrength(handToCompare.getDraw().charAt(i));
                if (myValue != otherValue) {
                    if (myValue > otherValue) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        }
        return 0;

    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    private int getCharacterStrength(char input){
        return characterOrder.indexOf(input);
    }

    public CamelHand getCamelHand() {
        return camelHand;
    }

    public void setCamelHand(CamelHand camelHand) {
        this.camelHand = camelHand;
    }

}

