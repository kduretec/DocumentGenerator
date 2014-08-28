package transformator;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.internal.runtime.Activator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowComponent;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowContext;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;

import PIMM.PIMMPackage;
import PSMM.PSMMPackage;

public class QVTTranformationCaller implements IWorkflowComponent{

	public void main() {
		System.out.println("Starting transformations");
		// Refer to an existing transformation via URI
		URI transformationURI = URI
				.createURI("file://home/kresimir/Projects/AGEP/workspace/Transformation/transforms/Transformation.qvto");
		// create executor for the given transformation
		TransformationExecutor executor = new TransformationExecutor(
				transformationURI);

		
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		PIMMPackage.eINSTANCE.eClass();
		PSMMPackage.eINSTANCE.eClass();
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("test", new XMIResourceFactoryImpl());
		m.put("psm", new XMIResourceFactoryImpl());
		
		// define the transformation input
		// Remark: we take the objects from a resource, however
		// a list of arbitrary in-memory EObjects may be passed
		// ExecutionContextImpl context = new ExecutionContextImpl();
		ResourceSet resourceSet = new ResourceSetImpl();

		Map<String, Object> opts = new HashMap<String, Object>();
		opts.put(XMIResource.OPTION_SCHEMA_LOCATION, true);

		for (int i = 0; i < 10; i++) {
			Resource inResource = resourceSet.getResource(
					URI.createURI("PIM/test" + i + ".test"), true);
			EList<EObject> inObjects = inResource.getContents();

			// create the input extent with its initial contents
			ModelExtent input = new BasicModelExtent(inObjects);
			// create an empty extent to catch the output
			ModelExtent output = new BasicModelExtent();

			// setup the execution environment details ->
			// configuration properties, logger, monitor object etc.
			ExecutionContextImpl context = new ExecutionContextImpl();
			context.setConfigProperty("keepModeling", true);

			// run the transformation assigned to the executor with the given
			// input and output and execution context -> ChangeTheWorld(in, out)
			// Remark: variable arguments count is supported
			ExecutionDiagnostic result = executor.execute(context, input,
					output);

			// check the result for success
			if (result.getSeverity() == Diagnostic.OK) {
				// the output objects got captured in the output extent
				List<EObject> outObjects = output.getContents();
				// let's persist them using a resource
				ResourceSet resourceSet2 = new ResourceSetImpl();
				Resource outResource = resourceSet2.createResource(
						URI.createURI("PSM/test" + i + ".psm"));
				outResource.getContents().addAll(outObjects);
				try {
					outResource.save(opts);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// turn the result diagnostic into status and send it to error
				// log
				IStatus status = BasicDiagnostic.toIStatus(result);
				//Activator.getDefault().getLog().log(status);
				System.out.println(status.getMessage());
				for (IStatus st : status.getChildren()) {
					System.out.println(st.getMessage());
				}
			}
		}
		System.out.println("Transformations done");
	}

	@Override
	public void preInvoke() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void invoke(IWorkflowContext ctx) {
		main();
		
	}

	@Override
	public void postInvoke() {
		// TODO Auto-generated method stub
		
	}

}
