import java.util.LinkedList;
import java.util.Iterator;

public class BitList extends LinkedList<Bit> {
    private int numberOfOnes;

    // Do not change the constructor
    public BitList() {
        numberOfOnes = 0;
    }

    // Do not change the method
    public int getNumberOfOnes() {
        return numberOfOnes;
    }

//----------write your code BELOW this line only!!!---------------------------------------------------------

//=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 2.1 ================================================

    public void addLast(Bit element) {
    	if(element==null) {
        	throw new UnsupportedOperationException("The element is null");
    	}
    	super.addLast(element);
    	if(element.toInt()==1) {
    		numberOfOnes++;
    	}
    }

    public void addFirst(Bit element) {
    	if(element==null) {
        	throw new UnsupportedOperationException("The element is null");       	
    	}
    	super.addFirst(element);
    	if(element.toInt()==1) {
    		numberOfOnes++;
    	}
    }

    public Bit removeLast() {
    	if(this.size()==0) {
        	throw new UnsupportedOperationException("The list is null");
    	}
    	Bit ans=super.removeLast();
    	if(ans.toInt()==1){
    		numberOfOnes--;
    	}
    	return ans;
    }

    public Bit removeFirst() {
    	if(this.size()==0) {
        	throw new UnsupportedOperationException("The list is null");
    	}
    	Bit ans=super.removeFirst();
    	if(ans.toInt()==1){
    		numberOfOnes--;
    	}
    	return ans;
    }

    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 2.2 ================================================
    public String toString() {
    	String ans="";
    	if(this.size()==0) {
    		ans=ans+'<'+'>';
    	}
    	else {
    		Iterator<Bit> iter=this.iterator();
    		while(iter.hasNext()) {
    			ans=ans+iter.next().toInt();
    		}
    	}
    	return ans;
    }


    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 2.3 ================================================
    public BitList(BitList other) {
    	if(other==null) {
    		throw new UnsupportedOperationException("The element is null");
    	}
    	BitList ans= new BitList();
    	ans.numberOfOnes=other.getNumberOfOnes();
    }

    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 2.4 ================================================
    public boolean isNumber() {
    	if(this==null) {
    		throw new NullPointerException();
    	}
    	if(this.size()==0) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }

    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 2.5 ================================================
    public boolean isReduced() {
    	if(this==null) {
    		throw new NullPointerException();
    	}
    	else if(this.size()==0) {
    		return false;
    	}
    	else if(this.size()==1) {
    		return true;
    	}
    	else {
    		BitList before=this;
    		this.reduce();
    		if(this.equals(before)) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    }

    public void reduce() {
    	Bit last;
    	for(int i=this.size();i>=2;i--) {
        	last=this.removeLast();
        	if(last.toInt()==1) {
        		this.addLast(last);
        		return;
        	}
    	}
   	}


    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 2.6 ================================================
    public Bit shiftRight() {
    	if(this==null) {
    		throw new NullPointerException();
    	}
    	if(this.size()==0) {
    		return null;
    	}
    	else if (this.size()==1) {
    		this.addFirst(new Bit(0));
    		return this.removeLast(); 
    		}
    	else {
    		return this.removeFirst();
    	}
    }

    public void shiftLeft() {
    	if(this==null) {
    		throw new NullPointerException();
    	}
    	this.addFirst(new Bit(0));
    }

    //=========================== Intro2CS 2024/1, ASSIGNMENT 4, TASK 2.7 ================================================
    public void padding(int newLength) {
    	if(this==null) {
    		throw new NullPointerException();
    	}
    	while(newLength>this.size()) {
    		this.shiftLeft();
    	}
    }


//=========================== Private methods of your own ================================================  



//--------------write your code ABOVE this line only!!!---------------------------------------------------------

    //----------------------------------------------------------------------------------------------------------
    // The following overriding methods must not be changed.
    //----------------------------------------------------------------------------------------------------------
    public boolean add(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public void add(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit remove(int index) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offer(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerFirst(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerLast(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit set(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Do not use this method!");
    }
}
