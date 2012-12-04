

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class UserInfoForm extends Frame implements ActionListener{
	Label label = new Label("User Information", Label.CENTER);
	Label name = new Label("Name");
	Label dob = new Label("DOB");
	Label sex = new Label("Sex");
	Label job = new Label("Job");
	TextField nameField = new TextField(30);
	Choice dlist = new Choice();
	Choice mlist = new Choice();
	Choice ylist = new Choice();
	CheckboxGroup sexGroup = new CheckboxGroup();
	Checkbox male = new Checkbox("Male", sexGroup, true);
	Checkbox female = new Checkbox("Female", sexGroup, false);
	Checkbox it = new Checkbox("IT",false);
	Checkbox sale = new Checkbox("Sale",false);
	Checkbox other = new Checkbox("Other",true);
	TextArea displayArea = new TextArea();
	Button save = new Button("Save");
	Button clear = new Button("Clear");
	Button cancel = new Button("Cancel");
	GridBagLayout gb;
	GridBagConstraints gbc;
	Boolean check = true;
	Label error = new Label();
	
	public UserInfoForm(){
		super();
		gb = new GridBagLayout();
		setLayout(gb);
		gbc = new GridBagConstraints();
		for (int i = 1; i < 32; i++) {
			dlist.addItem(String.valueOf(i));
			
		}
		mlist.add("Jan");
		mlist.add("Feb");
		mlist.add("Mar");
		mlist.add("Apr");
		mlist.add("May");
		mlist.add("Jun");
		mlist.add("Jul");
		mlist.add("Aug");
		mlist.add("Sep");
		mlist.add("Oct");
		mlist.add("Nov");
		mlist.add("Dec");
		for (int i = 2012; i > 1950; i--) {
			ylist.addItem(String.valueOf(i));
		}
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(label, 0, 0, 1, 7);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(name, 1, 0, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(nameField, 1, 1, 1, 6);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(dob, 2, 0, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(dlist, 2, 1, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(mlist, 2, 2, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(ylist, 2, 3, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(sex, 3, 0, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(male, 3, 1, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(female, 3, 2, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(job, 4, 0, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(it, 4, 1, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(sale, 4, 2, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(other, 4, 3, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(displayArea, 5, 0, 8, 7);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(save, 15, 1, 1, 1);
		save.addActionListener(this);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(clear, 15, 2, 1, 1);
		clear.addActionListener(this);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(cancel, 15, 3, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComp(error, 16, 1, 1, 6);
		error.setForeground(Color.red);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		UserInfoForm uiForm = new UserInfoForm();
		uiForm.setSize(500, 400);
		uiForm.setVisible(true);
	}
	
	public void addComp(Component comp, int row, int col, int nrow, int ncol) {
		gbc.gridx = col;
		gbc.gridy = row;
		gbc.gridwidth = ncol;
		gbc.gridheight = nrow;
		gb.setConstraints(comp, gbc);
		add(comp);
	}
	
	public class NameTooLongException extends NegativeArraySizeException {
		NameTooLongException(){
			super("Input Name Is Too Long");
		}
	}
	
	public int workingYearCalculate() throws TooYoungException{
		int year = Integer.parseInt(ylist.getSelectedItem());
		int age = 2012 - year;
		return age;
	}
	
	public class TooYoungException extends Exception {
		TooYoungException(){
			super("Too Young");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) throws NameTooLongException{
		String dateString = mlist.getSelectedItem() + " "+ String.valueOf(dlist.getSelectedItem()) +", " + String.valueOf(ylist.getSelectedItem());
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
		sdf.setLenient(false);
		
		try {
			check = true;
			if (nameField.getText().isEmpty()) {
				throw new IndexOutOfBoundsException();
			}
			if (nameField.getText().length() > 25) {
				throw new NameTooLongException();
			}
			sdf.parse(dateString);
			if (workingYearCalculate() < 16) {
				throw new TooYoungException();
			}
		} catch (IndexOutOfBoundsException e2) {
			// TODO: handle exception
//			displayArea.setText(" Name Empty");
			error.setText("Name Empty");
			check = false;
		} catch (NameTooLongException e2) {
			// TODO: handle exception
			error.setText("Name Too Long");
			check = false;
		} catch (ParseException e2) {
			// TODO: handle exception
			error.setText("Wrong Date Input");
			check = false;
		} catch (TooYoungException e2) {
			// TODO: handle exception
			error.setText("Too Young To Work");
			check = false;
		}
		
		String sexString;		
		sexString = sexGroup.getSelectedCheckbox().getLabel();
		
		String jobString = "";
		if (it.getState()) {
			jobString += it.getLabel() + "/";
		}
		if (sale.getState()) {
			jobString += sale.getLabel() + "/";
		}
		if (other.getState()) {
			jobString += other.getLabel();
		}
		
		if (e.getSource() == save) {
			if (check) {
				displayArea.setText(displayArea.getText() + "\n" + "Saved User Information:\nName: " + nameField.getText() + "\n"
						+ "DoB: " + dateString + "\n" + "Sex: " + sexString + "\n"
						+ "Job: " + jobString);
			}
		}
		if (e.getSource() == clear) {
			displayArea.setText("");
		}
	}
}
