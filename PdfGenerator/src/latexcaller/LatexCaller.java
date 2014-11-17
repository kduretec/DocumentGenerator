package latexcaller;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowComponent;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowContext;

public class LatexCaller implements IWorkflowComponent{

	public void main() {

		System.out.println("Compiling PDFs");
		for (int i = 0; i < 10; i++) {
			// Runtime rt = Runtime.getRuntime();
			String name = "test" + i;
			// Process pr;
			ProcessBuilder pb = new ProcessBuilder();
			File f = new File(
					"/home/kresimir/Projects/DocumentGenerator/DocumentGenerator/PdfGenerator/latex");
			//if (f.exists()) System.out.println("EXISTS");
			pb.directory(f);
			//System.out.println(pb.environment());
			try {
				pb.command("/usr/bin/latex", "" ,  name + ".tex");
				Process p1 = pb.start();
				try {
					p1.waitFor();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pb.command("/bin/bash", "-c", "/usr/bin/dvips "+ name + ".dvi");
				Process p2 = pb.start();
				try {
					p2.waitFor();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pb.command("/bin/bash", "-c","/usr/bin/ps2pdf " + name + ".ps");
				Process p3 = pb.start();
				try {
					p3.waitFor();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(name + " done");

		}
		System.out.println("Done compiling PDFs");
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
