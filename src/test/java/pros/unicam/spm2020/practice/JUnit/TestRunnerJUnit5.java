package pros.unicam.spm2020.practice.JUnit;

import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherFactory;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.launcher.listeners.TestExecutionSummary.Failure;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestRunnerJUnit5 {

	 public static void main(String args[]) {
			    final LauncherDiscoveryRequest request = 
			        LauncherDiscoveryRequestBuilder.request()
			                                   .selectors(selectClass(MyClassTest.class))
			                                   .selectors(selectClass(HelloWorldTest.class))
			                                   .build();

			        final Launcher launcher = LauncherFactory.create();
			        final SummaryGeneratingListener listener = new SummaryGeneratingListener();

			        launcher.registerTestExecutionListeners(listener);
			        launcher.execute(request);

			        TestExecutionSummary summary = listener.getSummary();
			        
			        
			        System.out.println("getTimeStarted() - " + summary.getTimeStarted());
			        System.out.println("getTestsFoundCount() - " + summary.getTestsFoundCount());
			        System.out.println("getTestsSucceededCount() - " + summary.getTestsSucceededCount());
			        System.out.println("getTestsFailedCount() - " + summary.getTestsFailedCount());
			        System.out.println("getTotalFailureCount() - " + summary.getTotalFailureCount());
			        System.out.println("getTimeFinished() - " + summary.getTimeFinished());
			        System.out.println("TotalTime() - " + (summary.getTimeFinished() - summary.getTimeStarted()));
			        			        
			        List<Failure> failures = summary.getFailures();
			        failures.forEach(failure -> System.out.println("failure - " + failure.getException()));
			        
			        //SAVE TO A RESULT.TXT FILE
			        // Save the System.out instance
			        PrintStream oldPrintStream = System.out;

			        FileOutputStream outFile = null;

			        try {

			            outFile = new FileOutputStream("result.txt");

			            PrintStream newPrintStream = new PrintStream(outFile);
			            System.setOut(newPrintStream);

			            
			            System.out.println("getTimeStarted() - " + summary.getTimeStarted());
				        System.out.println("getTestsFoundCount() - " + summary.getTestsFoundCount());
				        System.out.println("getTestsSucceededCount() - " + summary.getTestsSucceededCount());
				        System.out.println("getTestsFailedCount() - " + summary.getTestsFailedCount());
				        System.out.println("getTotalFailureCount() - " + summary.getTotalFailureCount());
				        System.out.println("getTimeFinished() - " + summary.getTimeFinished());
				        System.out.println("TotalTime() - " + (summary.getTimeFinished() - summary.getTimeStarted()));
				        			        
				        List<Failure> failures2 = summary.getFailures();
				        failures2.forEach(failure -> System.out.println("failure - " + failure.getException()));
			            

			        } catch (FileNotFoundException ex) {
			            Logger.getLogger(TestRunnerJUnit5.class.getName()).log(Level.SEVERE, null, ex);
			            
			        } finally {
			        	
			            // Reset the old System.out instance
			            System.setOut(oldPrintStream);          
			            System.out.println("Testing Completed! Check output folder for result.");

			            try {
			                outFile.close();
			            } catch (IOException ex) {
			                Logger.getLogger(TestRunnerJUnit5.class.getName()).log(Level.SEVERE, null, ex);
			            }
			        }
			    }
	 
}
