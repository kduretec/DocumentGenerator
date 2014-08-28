package compiler;

import PSMM.Document;
import PSMM.Element;
import PSMM.NewPage;
import PSMM.PSMMPackage;
import PSMM.Table;
import PSMM.Text;
import com.google.common.collect.Iterables;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowComponent;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowContext;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class PSMCompiler implements IWorkflowComponent {
  public void main() {
    InputOutput.<String>println("Starting code generation");
    this.run();
    InputOutput.<String>println("Code generation done");
  }
  
  public void run() {
    try {
      PSMMPackage.eINSTANCE.eClass();
      Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
      Map<String, Object> m = reg.getExtensionToFactoryMap();
      XMIResourceFactoryImpl _xMIResourceFactoryImpl = new XMIResourceFactoryImpl();
      m.put("psm", _xMIResourceFactoryImpl);
      ResourceSet resSet = new ResourceSetImpl();
      for (int i = 0; (i < 10); i++) {
        {
          String name = ("test" + Integer.valueOf(i));
          File f = new File((("latex/" + name) + ".tex"));
          FileWriter _fileWriter = new FileWriter(f);
          BufferedWriter bw = new BufferedWriter(_fileWriter);
          URI _createURI = URI.createURI((("PSM/" + name) + ".psm"));
          Resource resource = resSet.getResource(_createURI, true);
          TreeIterator<EObject> _allContents = resource.getAllContents();
          Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
          Iterable<Document> _filter = Iterables.<Document>filter(_iterable, Document.class);
          for (final Document e : _filter) {
            {
              CharSequence temp = this.compileDocument(e);
              String _string = temp.toString();
              bw.write(_string);
            }
          }
          bw.close();
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public CharSequence compileDocument(final Document d) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\documentclass[12pt]{article}");
    _builder.newLine();
    _builder.append("\\begin{document}");
    _builder.newLine();
    String _compileElements = this.compileElements(d);
    _builder.append(_compileElements, "");
    _builder.newLineIfNotEmpty();
    _builder.append("\\end{document}");
    _builder.newLine();
    return _builder;
  }
  
  public String compileElements(final Document d) {
    StringConcatenation _builder = new StringConcatenation();
    String temp = _builder.toString();
    EList<Element> _content = d.getContent();
    for (final Element e : _content) {
      String _switchResult = null;
      boolean _matched = false;
      if (!_matched) {
        if (e instanceof Text) {
          _matched=true;
          _switchResult = this.compileText(((Text)e));
        }
      }
      if (!_matched) {
        if (e instanceof Table) {
          _matched=true;
          _switchResult = this.compileTable(((Table)e));
        }
      }
      if (!_matched) {
        if (e instanceof NewPage) {
          _matched=true;
          _switchResult = this.compileNewPage(((NewPage)e));
        }
      }
      String _plus = (temp + _switchResult);
      temp = _plus;
    }
    return temp;
  }
  
  public String compileText(final Text e) {
    StringConcatenation _builder = new StringConcatenation();
    String _textvalue = e.getTextvalue();
    _builder.append(_textvalue, "");
    _builder.newLineIfNotEmpty();
    String temp = _builder.toString();
    return temp;
  }
  
  public String compileTable(final Table e) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder.toString();
  }
  
  public String compileNewPage(final NewPage e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\newpage");
    _builder.newLine();
    String temp = _builder.toString();
    return temp;
  }
  
  public void invoke(final IWorkflowContext ctx) {
    this.main();
  }
  
  public void postInvoke() {
  }
  
  public void preInvoke() {
  }
}
