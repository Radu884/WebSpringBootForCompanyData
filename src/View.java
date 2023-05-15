
@PageTitle("Contacts ")
@Route(value = "")


public class View extends Layout{
     Grid<Contact> grid = new Grid<>(Contact.class);
     TextField filtertext = new TextField();
     ContactForm form;
     
     public View() {    	 
    	 addClassName("list-view");
    	 setSizeFull();   	 
    	 configureGrid(); 
    	 configureForm();	
    	 add(
    			getToolbar(),
    			grid
    			 );   	    	 
     } 
       private Component getContent() {
    	  Layout content = new Layout(grid, form);
    	  content.setFlexGrow(flexGrow: 3, grid);
    	  content.setFlexGrow(flexGrow: 4, form);
    	  content.addClassName("content");
    	  content.setSizeFull();  	     	   
    	   return content;  	   
       }
     private void configureForm() {
    	 form = new ContactForm(Collections.emptyList(), Collections.emptyList());
    	 form.setWidth("25em"); 
    	 form.addListener(ContactForm.SaveEvent.class, this::saveContact);
    	 form.addListener(ContactForm.DeleteEvent.class, this::deleteContact);
    	 form.addListener(ContactForm.CloseEvent.class, e -> closeEditor());
    	 
    	 
     }       
         private void saveContact(ContactForm.SaveEvent event) {
        	 service.saveContact(event.getContact());
        	 updateList();
        	 closeEditor();
         }
         
         private void deleteContact(ContactForm.DeleteEvent event) {
        	 service.deleteContact(event.getContact());
        	 updateList();
        	 closeEditor();
         }
     
           private Component getToolbar() {
        	   filterText.setPlaceholder("Filter by name");
        	   filterText.setClearButtonVisible(true);
        	   filterText.setValueChangeMode(ValueChangeMode.LAZY);
        	   filterText.addValueChangeListener(e -> updateList());
        	   
        	   Button addContact = new Button("Add");
        	   addContactButton.addClickListener(e -> addContact());
        	   
        	   HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        	   toolbar.addClassName("toolbar");
        	   return toolbar;
           }
           private void addContact() {
        	   grid.asSingleSelect().clear();
        	   editContact(new Contact());
        	   
           }
           
     private void configureGrid() {
    	 grid.addClassName("Contact");
    	 grid.setSizeFull();
    	 grid.setColumns("First Name","Last Name","Age","Email");
    	 grid.addColumn(contact -> contact.getCompany().getName().setHeader("Information"));
    	 grid.addColumn(contact -> contact.getCompany().getName().setHeader("Business"));
    	 grid.getColumn().forEach(col -> col.setAutoWidth(true));
     }
     
     
