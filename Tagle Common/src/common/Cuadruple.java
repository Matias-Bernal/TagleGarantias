package common;

import java.io.Serializable;

/**
 *	Implements an immutable 3-uple. 
*/
public class Cuadruple<A,B,C,D> implements Serializable {

	protected static final long serialVersionUID = 1L;

	// Private attributes
	
	private A _first;
	
	private B _second;
	
	private C _third;
	
	private D _fourth;
	
	// Construction
	
	public Cuadruple(A fst, B snd, C trd, D fth) {
		_first = fst;
		_second = snd;
		_third = trd;
		_fourth = fth;
	}
	
    //Seters
    public void setFirst(A fst){ 
	_first = fst;
	}
	
	public void setSecond(B snd){ 
		_second = snd;
	}
	
	public  void setThird(C trd){ 
		_third = trd;
	}
	
	public  void setFourth(D fth){ 
		_fourth = fth;
	}	
        
	// Getters
	
	public A first() { 
		return _first;
	}
	
	public B second() { 
		return _second;
	}
	
	public C third() { 
		return _third;
	}

	public D fourth() { 
		return _fourth;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_first == null) ? 0 : _first.hashCode());
		result = prime * result + ((_second == null) ? 0 : _second.hashCode());
		result = prime * result + ((_third == null) ? 0 : _third.hashCode());
		result = prime * result + ((_fourth == null) ? 0 : _fourth.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuadruple other = (Cuadruple) obj;
		if (_first == null) {
			if (other._first != null)
				return false;
		} else if (!_first.equals(other._first))
			return false;
		if (_second == null) {
			if (other._second != null)
				return false;
		} else if (!_second.equals(other._second))
			return false;
		if (_third == null) {
			if (other._third != null)
				return false;
		} else if (!_third.equals(other._third))
			return false;
		if (_fourth == null) {
			if (other._fourth != null)
				return false;
		} else if (!_fourth.equals(other._fourth))
			return false;
		return true;
	}
	
}

