import java.util.*;

public class LinkedList<Object> implements Iterable<Object>{

	node head;

	class node{
		Object data;
		node next;

		public Object getData(){return data;}
	}
	public node getHead(){return head;}
	public Boolean IsEmpty() {
		if(head == null) return true;
		return false;
	}
	public Boolean IsMember(Object o) {

		node temp = head;
		while( temp != null ) {
			if(temp.data.equals(o)) return true;
			temp = temp.next;
		}

		return false;
	}
	public void addMember(Object o) {
		if(IsMember(o))
			return;
		node newnode = new node();
		newnode.data = o;
		newnode.next = null;

		if(head == null){
			head = newnode;
			return;
		}

		node temp = head;
		while(temp.next != null)
			temp = temp.next;

		temp.next = newnode;
		return;
	}
	public int numberOfMembers(){
		node temp = new node();
		temp = head;
		int count = 0;
		while(temp != null){
			count++;
			temp = temp.next;
		}
		return count;
	}
	public void deleteMember(Object o) {

		if( IsEmpty())
			throw new IllegalArgumentException();
		if( !(IsMember(o)) )
			throw new NoSuchElementException();

		else{
			node temp = new node();
			temp = head;

			while( temp != null ) {
				if(temp.data.equals(o)) return;

				if(temp.next.data.equals(o)) {
					temp.next = temp.next.next;
					return;
				}
				temp = temp.next;
			}
		}
	}

	public Iterator<Object> iterator() { return new ListIterator(); }
  private class ListIterator implements Iterator<Object>{
      private node current = head;
      public boolean hasNext() { return current != null; }
      public void remove() { throw new UnsupportedOperationException(); }
      public Object next()
      {
          if(hasNext()==false)
              throw new NoSuchElementException();
          else
          {
              Object o = current.data;
              current = current.next;
              return o;
          }
      }
  }
	public static void main(String[] args) {
		String i1=  "sa";
		String i2 = "fs";
		String i3 = "sd";
		LinkedList<String> list = new LinkedList<String>();
		list.addMember(i1);
		list.addMember(i2);
		list.addMember(i3);
		list.deleteMember(i2);

		for (String s : list) {
			System.out.println(s);
		}
	}
}
