package compiler

import PSMM.Document
import PSMM.Element
import PSMM.NewPage
import PSMM.PSMMPackage
import PSMM.Table
import PSMM.Text
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.Map
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl

class PSMCompiler {
	def static void main(String[] args) {

		var PSMCompiler com = new PSMCompiler();
		com.run()
	}

	def run() {
		PSMMPackage.eINSTANCE.eClass();
		var Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		var Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("psm", new XMIResourceFactoryImpl());

		var ResourceSet resSet = new ResourceSetImpl();

		for (var i = 0; i < 10; i++) {
			var name = "test" + i
			var File f = new File("latex/" + name + ".tex")
			var BufferedWriter bw = new BufferedWriter(new FileWriter(f))
			var resource = resSet.getResource(URI::createURI("PSM/" + name + ".psm"), true)
			for (e : resource.allContents.toIterable.filter(typeof(Document))) {
				var temp = compileDocument(e)
				println(temp)
				bw.write(temp.toString)
			}
			bw.close()
		}

	}

	def compileDocument(Document d) '''
		\documentclass[12pt]{article}
		\begin{document}
		«compileElements(d)»
		\end{document}
	'''

	def compileElements(Document d) {
		var temp =''''''
		for (Element e : d.content) {
			temp = temp + switch e {
				Text: compileText(e)
				Table: compileTable(e)
				NewPage: compileNewPage(e)
			}
		}
		return temp
	}

	def compileText(Text e) {
		var temp ='''«e.textvalue»
		'''
		return temp;
	}
	
	def compileTable(Table e) {
		return ''''''
	}
	
	def compileNewPage(NewPage e) {
		var temp ='''
		\newpage
		'''
		return temp 
	}
}
