package groundtruth.ocl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowComponent;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowContext;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;

import PIMM.Document;
import PIMM.PIMMPackage;

public class Evaluator implements IWorkflowComponent{

	public void main() throws IOException {
		
		System.out.println("Extracting ground truth elements");
		OCL ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
		OCLHelper helper = ocl.createOCLHelper();
		helper.setContext(PIMMPackage.Literals.DOCUMENT);
		try {
			OCLExpression<EClassifier> query = helper
					.createQuery("self.pages->size()");

			Query eval = ocl.createQuery(query);
			
			List<Document> documents = getDocuments();
			
			for (Document doc : documents) {
				String name = doc.getName();
				File f = new File("groundtruth/"+name+".gt");
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));
				bw.write("number_of_pages=" + eval.evaluate(doc));
				//System.out.println(eval.evaluate(doc));
				bw.close();
			}
			
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ground thruth extracted");
	}

	private static List<Document> getDocuments() {
		List<Document> docs = new ArrayList<Document>();
		
		PIMMPackage.eINSTANCE.eClass();
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	    Map<String, Object> m = reg.getExtensionToFactoryMap();
	    m.put("test", new XMIResourceFactoryImpl());
	    
	    ResourceSet resSet = new ResourceSetImpl();
	    
	    for (int i=0; i<10; i++) {
	    	Resource resource = resSet.getResource(URI
	    	        .createURI("PIM/test"+i+".test"), true);
	    	Document doc = (Document) resource.getContents().get(0);
	    	docs.add(doc);
	    }
	    
	    return docs;
	}

	@Override
	public void preInvoke() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void invoke(IWorkflowContext ctx) {
		try {
			main();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void postInvoke() {
		// TODO Auto-generated method stub
		
	}
}
