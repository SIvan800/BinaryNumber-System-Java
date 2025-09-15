
import java.util.Iterator;

public class BinaryNumber implements Comparable<BinaryNumber> {

    private BitList bits;

    private static BinaryNumber Zero() {
        return new BinaryNumber(0);
    }

    private static BinaryNumber One() {
        return new BinaryNumber(1);
    }

    //Do not change this constructor
    private BinaryNumber(int i) {
        bits = new BitList();
        if (i == 0)
            bits.addFirst(Bit.ZERO);
        if (i == 1)
            bits.addFirst(Bit.ONE);
        else if (i != 0)
            throw new IllegalArgumentException("This Constructor may only get either zero or one.");
    }

    // Copy constructor
    //Do not change this constructor
    public BinaryNumber(BinaryNumber number) {
        bits = new BitList(number.bits);
    }


    //Do not change this method
    public int length() {
        return bits.size();
    }

    //Do not change this method
    public boolean isLegal() {
        return bits.isNumber() & bits.isReduced();
    }


    //----------write your code BELOW this line only!!!---------------------------------------------------------

    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 3.1 ================================================
    public BinaryNumber(char c) {
    	if(!(c-'0'>=0 && c-'0'<=9)) {
    		throw new UnsupportedOperationException("Delete this line and implement the method.");
    	}
    	int value=c-'0';
    	int mod=0; 
    	BitList list=new BitList();
    	while(value!=0 && value!=1) {
    		mod=value%2;
    		value=value/2;
    		Bit bit=new Bit(mod);
    		list.addFirst(bit);
    	}
		Bit bit=new Bit(value);
		list.addFirst(bit);
		this.bits=list;
    }

    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 3.2 ================================================
    public String toString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
    	String ans="";
    	if(bits.size()!=0) {
    		Iterator<Bit> iter=bits.iterator();
    		while(iter.hasNext()) {
    			ans=ans+iter.next().toInt();
    		}
    	}
    	return ans;
    }

    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 3.3 ================================================
    public boolean equals(Object other) {
    	if(other==null) {
        	throw new UnsupportedOperationException("Delete this line and implement the method.");
    	}
    	boolean ans =false;
    	
    	if(other instanceof BinaryNumber) {
    		this.bits.reduce();
    		((BinaryNumber)other).bits.reduce();
    		if(this.bits.size()==((BinaryNumber)other).bits.size()) {
    			Iterator<Bit> iterA = this.bits.iterator();
    			Iterator<Bit> iterB = ((BinaryNumber)other).bits.iterator();
    			boolean tempAns=true;
    			while(iterA.hasNext() && tempAns) {
    				if(!(iterA.next().equals(iterB.next()))) {
    					tempAns=false;
    				}
    			}
    			ans=tempAns;
    		}
    	}
    	return ans;
    }

    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 3.4 ================================================
    public BinaryNumber add(BinaryNumber addMe) {
    	if(addMe==null) {
    	throw new UnsupportedOperationException("Delete this line and implement the method.");
    	}
    	Iterator<Bit> iterA=this.bits.iterator();
    	Iterator<Bit> iterB=addMe.bits.iterator();
    	BitList list=new BitList();
    	Bit zero=new Bit(0);
    	
		Bit nextA=new Bit(iterA.next().toInt());
		Bit nextB=new Bit(iterB.next().toInt());
		list.addLast(fullAdderSum(nextA,nextB,zero));
		Bit carry = new Bit((fullAdderCarry(nextA,nextB,zero)).toInt());
		
    	while(iterA.hasNext()&&iterB.hasNext()) {
    		nextA=new Bit(iterA.next().toInt());
    		nextB=new Bit(iterB.next().toInt());
    		list.addLast(fullAdderSum(nextA,nextB,carry));
    		carry = new Bit((fullAdderCarry(nextA,nextB,carry)).toInt());
    	}
    	
    	if(iterA.hasNext()&&!iterB.hasNext()) {
    		nextA=new Bit(iterA.next().toInt());
    		list.addLast(fullAdderSum(nextA,zero,carry));
    		carry = new Bit((fullAdderCarry(nextA,zero,carry)).toInt());
    	}

    	
    	else if(!iterA.hasNext()&&iterB.hasNext()) {
    		nextB=new Bit(iterB.next().toInt());
    		list.addLast(fullAdderSum(nextB,zero,carry));
    		carry = new Bit((fullAdderCarry(nextB,zero,carry)).toInt());
    	}

    	return this;
    }
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



    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 3.5 ================================================
    public int compareTo(BinaryNumber other) {
        if(this==null || other==null){
    	    throw new UnsupportedOperationException("Delete this line and implement the method.");
        }
        
        int ans=0;
        
        this.bits.reduce();
        other.bits.reduce();
        int thisL = this.length();
        int otherL = other.length();
        int comp = thisL-otherL;
        if(comp<0){
            ans=-1;
        }
        else if(comp>0) {
        	ans=1;
        }
        else{
            BitList thisList=new BitList(this.bits);
            BitList otherList=new BitList(other.bits);
            Bit thisCurr;
            Bit otherCurr;
            while(thisList.size()>0 && ans==0){
                thisCurr = thisList.removeFirst();
                otherCurr = otherList.removeFirst();
                if(!(thisCurr.equals(otherCurr))){
                    ans=(thisCurr.toInt())-(otherCurr.toInt());
                }
            }
        }
        return ans;
    }


    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 3.6 ================================================
    public BinaryNumber subtract(BinaryNumber subtractMe) {
    	if(this==null|| subtractMe==null) {
    		throw new NullPointerException("The Number is null");
    	}
    	if(this.compareTo(subtractMe)==-1) {
    		throw new IllegalArgumentException("The substraction cannot br done");
    	}
    	else if(this.compareTo(subtractMe)==-1) {
    		return new BinaryNumber(0);
    	}
    	else {
    		BinaryNumber ans=new BinaryNumber(0);
    		
    		return ans;
    	}
    }
    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 3.7 ================================================
    public int toInt() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        BitList thisL=this.bits;
        Iterator<Bit> iter=thisL.iterator();
        int exp=1;
        int curr=0;
        int ans=0;
        while(iter.hasNext()) {
        	curr=iter.next().toInt();
        	ans=ans+(curr*exp);
        	exp=exp*2;
        }
        return ans;
    }

    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 3.8 ================================================
    public BinaryNumber multiply(BinaryNumber multiplyMe) {
    	if(this==null||multiplyMe==null) {
        	throw new UnsupportedOperationException("The number is null");
    	}
    	BitList ans=new BitList();
		Iterator<Bit> iterA=(multiplyMe.bits).iterator();

    	if(this.bits.size() > multiplyMe.bits.size()) {
    		Iterator<Bit> iter=(multiplyMe.bits).iterator();
    		BitList curr= this.bits;
    	}
    	else {
    		Iterator<Bit> iter=(this.bits).iterator();
    		BitList curr=multiplyMe.bits;
    	}
    	while(iterA.hasNext()) {
    		if(iterA.next().toInt()==1) {
    			
    		}
    		
    	}
    	
    }

    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 3.9 ================================================
    public BinaryNumber divide(BinaryNumber divisor) {
    	throw new UnsupportedOperationException("Delete this line and implement the method.");
    }


    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 3.10 ================================================
    public BinaryNumber mod(BinaryNumber modulus) {
    	throw new UnsupportedOperationException("Delete this line and implement the method.");
    }

    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 3.11 ================================================
    public BinaryNumber(String s) {
    	throw new UnsupportedOperationException("Delete this line and implement the method.");
    }

    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 3.12 ================================================
    public String toIntString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line

    	throw new UnsupportedOperationException("Delete this line and implement the method.");
    }

//=========================== Private methods of your own ================================================  



//--------------write your code ABOVE this line only!!!---------------------------------------------------------

    // Returns this * 2
    // Do not change this method
    public BinaryNumber multiplyBy2() {
        BinaryNumber output = new BinaryNumber(this);
        output.bits.shiftLeft();
        output.bits.reduce();
        return output;
    }

    // Returns this / 2
    // Do not change this method
    public BinaryNumber divideBy2() {
        BinaryNumber output = new BinaryNumber(this);
        if (!equals(Zero()))
            output.bits.shiftRight();
        return output;
    }

}


