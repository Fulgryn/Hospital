package Prototype;
import java.util.*;


public class PrototypeTester
{
   public static void main(String[] args)
   {
	   int menu_option;
	   Scanner keyIn = new Scanner(System.in);
	   Patient patient = new Patient();
	   Ward ward = new Ward();

	   do{
		   System.out.println("\t\tADMIN MENU");
		   System.out.println("\t1. Admit patient");
		   System.out.println("\t2. Manage Ward");
		   menu_option=keyIn.nextInt();
		   
		   if(menu_option == 1)
		   {
			   System.out.println("\t\tPatient Menu");
			   System.out.println("\t1. Add patient");
			   System.out.println("\t2. View Patient");
			   System.out.println("\t3. Delete patient");
			   menu_option = keyIn.nextInt();
			   switch(menu_option)
			   {
			     case 1://add patient
			    	keyIn.nextLine();
			    	System.out.println("Enter FIRST NAME:");
				    String patientFirstName = keyIn.nextLine();
				    
				    System.out.println("Enter LAST NAME:");
				    String patientLastName = keyIn.nextLine();
				    System.out.println("enter date of birth");
				    String DOB = keyIn.nextLine();
				    while(!DOB.matches("^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$"))
				    {
				    	System.out.println("The date of birth entered does not match the requirements\n Enter new date of birth");
				    	DOB = keyIn.nextLine();
				    }
				    System.out.println("enter address");
				    String address = keyIn.nextLine();
				    System.out.println("enter email");
				    String email = keyIn.nextLine();
				    System.out.println("enter phone number");
				    String phoneNB = keyIn.nextLine();
				    while(!phoneNB.matches("^\\d{10}$"))
				    {
				    	System.out.println("The phone number entered does not match the requirements\n Enter new phone number");
				    	phoneNB = keyIn.nextLine();
				    }
			        System.out.println("Enter PPS Number:");
			        String patientPPS = keyIn.nextLine();
			        while(!patientPPS.matches("^\\d{7}[A-Z]{2}$")&&!patientPPS.matches("^\\d{7}[A-Z]{1}\\s{1}$"))
				    {
			        	if(patientPPS.matches("^\\d{7}[A-Z]{1}$"))
			        		patientPPS=patientPPS+" ";
			        	else
			        	{
					    	System.out.println("The PPS number entered does not match the requirements\n Enter new PPS number");
					    	patientPPS = keyIn.nextLine();	
			        	}
				    }
			        System.out.println("Enter next of kin");
			        String patientnok=keyIn.nextLine();
			        System.out.println("enter allergy");
			        String allergy = keyIn.nextLine();
			        System.out.println("enter history");
			        String history = keyIn.nextLine();
			        System.out.println("enter referance");
			        String referance = keyIn.nextLine();
			        System.out.println("enter refferal class");
			        String referance_class = keyIn.nextLine();
			        Patient addpatient = new Patient(patientFirstName, patientLastName, DOB, address, email, phoneNB, patientPPS, patientnok, allergy, history, referance, referance_class );
			        int addStatus = Patient.add(addpatient);
			        if(addStatus==1)
			        {
			        	System.out.println("patient added to datbase");
			        }
			        break;
			    /*case 2: //search patient
			    	 int searchpatient=0;
			    	 System.out.println("\n\nEnter the hospital id of the patient");
			    	 searchpatient=keyIn.nextInt();
			    	 Patient search = patient.searchAPatient(searchpatient);
			    	 System.out.println(search.getID()+" \t"+
			    	                    search.getPPS()+" \t"+
			    			            search.GetRefferal());
			    	 break;*/
			    case 2: //view all patients
			    	ArrayList<Patient> allPatients = Patient.viewAllPatients();
			    	System.out.println("view all patients");
			    	//System.out.println("Number \thospital is \tPPSN number \trefferal");
			    	Iterator<Patient> patientIterator = allPatients.iterator();
			    	while(patientIterator.hasNext())
			    	{
			    		Patient DisplayPatient = patientIterator.next();
			    		System.out.println(DisplayPatient.toString());
			    	}
			    	break;
			    case 3: //delete a patient
			    	int idToDel=0;
					System.out.println("\n\nList of patients:");
					   
					ArrayList arptoDel= new ArrayList();
					arptoDel=Patient.viewAllPatients();
					for(final Object p:arptoDel){
						System.out.println(p.toString());
					}
					System.out.println("\n\nEnter the ID of the patient to delete:");
					idToDel=keyIn.nextInt();
			        int delStatus=Patient.deletePatient(idToDel);
			        break;
			   }
		   }
		   else if(menu_option == 2)
		   {
			   System.out.println("\t\tWARD MENU");
			   System.out.println("\t1. Assign patient to ward");
			   System.out.println("\t2. remove patient from ward"); 
			   menu_option = keyIn.nextInt();
			   
			   switch(menu_option)
			   {
			   case 1: //Assign patient to ward
				   int searchHost=0;
				   System.out.println("\n\nList of patients:");
				   
				   ArrayList arp= new ArrayList();
				   arp=Patient.viewAllPatients();
				   for(final Object p:arp){
						System.out.println(p.toString());
				   }
				   System.out.println("\n\nEnter the ID of the patient to add:");
				   searchHost=keyIn.nextInt();
				   /*Ward searchpatient = ward.search(searchHost);
				   System.out.println(searchpatient.getID()+"\t"+
			                          searchpatient.GetRefferalClass());*/
				   
				   System.out.println("\n\nList of wards:");
				   
				   ArrayList arw= new ArrayList();
				   arw=Ward.viewAllWards();
				   for(final Object w:arw){
						System.out.println(w.toString());
				   }
		           System.out.println("Enter ward number");
		           int wardNum = keyIn.nextInt();
		           int addStatus = Ward.assignWard(wardNum,searchHost);
		           if(addStatus == 1)
		           {
		        	   System.out.println("record added to database");
		           }
				   break;
			   case 2: //remove patient from ward
				   int delfromWard=0;
				   System.out.println("\n\nList of patients:");
				   
				   ArrayList arp2= new ArrayList();
				   arp2=Patient.viewAllPatients();
				   for(final Object p:arp2){
						System.out.println(p.toString());
				   }
				   System.out.println("\n\nEnter the ID of the patient to remove from its ward:");
				   delfromWard=keyIn.nextInt();
				   /*Ward searchpatient = ward.search(searchHost);
				   System.out.println(searchpatient.getID()+"\t"+
			                          searchpatient.GetRefferalClass());*/
				   int delStatus = Ward.removeFromWard(delfromWard);
				   break;
			   }
		   }
		   
	   }while(menu_option != 4);
   }
}
