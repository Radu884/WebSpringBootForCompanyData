import java.util.List;

public class Form extends Layout {
         
	 TextField firstName = new TextField(label: "First name");
	 TextField lastName = new TextField(label: "Last name");
	 TextField age = new TextField(label: "Age");
	 EmailField email = new EmailField(label: "Email");
	 ComboBox<Status> status = new ComboBox<>(label: "Status");
	 ComboBox<Company> company = new ComboBox<>(label: "Company");
	 
	 Button save = new Button(text: "Save");
	 Button delete = new Button(text: "Delete");
	 Button cancel = new Button(text: "Cancel");
	 
	 public ContactForm(List<Company> companies, List<Status> statuses) {
		 addClassName("contact-form");
		 
		 company.setItems(companies);
		 company.setItemsLabelGenerator(Company::getName);
		 
		 status.setItems(statuses);
		 status.setItemLabelGenerator(Status::getName);
		 
		 add(
				firstName,
				lastName,
				age,
				email,
				company,
				status,
				createButtonLayout()			 
				 );	 		 
	 }
	 
	 private Component createButtonLayout() {
		 save.addTheme(ButtonVariant.PRIMARY);
		 delete.addTheme(ButtonVariant.ERROR);
		 cancel.addTheme(ButtonVariant.TERTIARY);
		 
		 save.addClickShortcut(Key.ENTER);
		 cancel.addClickShortcut(Key.ESCAPE);
		
		 return new Layout(save,delete,cancel);
	 }
}
