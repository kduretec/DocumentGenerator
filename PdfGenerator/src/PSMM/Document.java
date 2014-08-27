/**
 */
package PSMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link PSMM.Document#getContent <em>Content</em>}</li>
 *   <li>{@link PSMM.Document#getDocname <em>Docname</em>}</li>
 * </ul>
 * </p>
 *
 * @see PSMM.PSMMPackage#getDocument()
 * @model
 * @generated
 */
public interface Document extends EObject {
	/**
	 * Returns the value of the '<em><b>Content</b></em>' containment reference list.
	 * The list contents are of type {@link PSMM.Element}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content</em>' containment reference list.
	 * @see PSMM.PSMMPackage#getDocument_Content()
	 * @model containment="true"
	 * @generated
	 */
	EList<Element> getContent();

	/**
	 * Returns the value of the '<em><b>Docname</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Docname</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Docname</em>' attribute list.
	 * @see PSMM.PSMMPackage#getDocument_Docname()
	 * @model
	 * @generated
	 */
	EList<String> getDocname();

} // Document
