package utils;

import java.util.ArrayList;
import java.util.ListIterator;
import alg.TreeEdit;

public class EditList {
	private ArrayList<TreeEdit> editList;
	
	public EditList() {
		editList = new ArrayList<TreeEdit>();
	}
	
	public ListIterator<TreeEdit> getListIterator() {
		return editList.listIterator();
	}
	
	public void addEdit(TreeEdit te) {
		editList.add(te);
	}
	
	public void addEditToFront(TreeEdit te) {
		editList.add(0, te);
	}
	
	public void addEdits(EditList el) {
		editList.addAll(el.getList());
	}
	
	public TreeEdit getEdit(int position) {
		return (editList.get(position));
	}
	
	public void reset() {
		editList.clear();
	}
	
	public ArrayList<TreeEdit> getList() {
		return editList;
	}
	
	public int size () {
		return editList.size();
	}
	
	public EditList clone() {
		EditList el = new EditList();
		el.addEdits(this);
		return el;
	}
}
