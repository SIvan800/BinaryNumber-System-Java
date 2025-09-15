public class Bit {

    private final boolean value;
    public static final Bit ONE = new Bit(true);
    public static final Bit ZERO = new Bit(false);

    public Bit(boolean value) {
        this.value = value;
    }

    public Bit(int intValue) {
        if (intValue == 0)
            value = false;
        else {
            if (intValue == 1)
                value = true;
            else throw new IllegalArgumentException(intValue + " is neither 0 nor 1.");
        }
    }

    public String toString() {
        return "" + toInt();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Bit))
            return false;
        else return value == ((Bit) obj).value;
    }


    public int toInt() {
        int output;
        if (value)
            output = 1;
        else
            output = 0;
        return output;
    }

    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 1.1 ================================================
    //--------------Write your code below this line only-----------------------------------------------------------
    public static Bit fullAdderSum(Bit bit1, Bit bit2, Bit bit3) {
    	int sum3bits=bit1.toInt()+bit2.toInt()+bit3.toInt();
    	int ans=0;
    	if(sum3bits==3||sum3bits==1) {
    		ans=1;
    	}
    	else if(sum3bits==2||sum3bits==0) {
    		ans=0;
    	}
    	return new Bit(ans);
    }

    public static Bit fullAdderCarry(Bit bit1, Bit bit2, Bit bit3) {
    	int sum3bits=bit1.toInt()+bit2.toInt()+bit3.toInt();
    	int ans=0;
    	if(sum3bits==3||sum3bits==2) {
    		ans=1;
    	}
    	else if(sum3bits==1||sum3bits==0) {
    		ans=0;
    	}
    	return new Bit(ans);
    }

//=========================== Private methods of your own ================================================  



//--------------write your code ABOVE this line only!!!---------------------------------------------------------


}
