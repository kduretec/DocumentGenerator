/**
 */
package PSMM.impl;

import PSMM.PSMMPackage;
import PSMM.Text;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Text</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link PSMM.impl.TextImpl#getTextvalue <em>Textvalue</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TextImpl extends ElementImpl implements Text {
	/**
	 * The default value of the '{@link #getTextvalue() <em>Textvalue</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextvalue()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXTVALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTextvalue() <em>Textvalue</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextvalue()
	 * @generated
	 * @ordered
	 */
	protected String textvalue = TEXTVALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PSMMPackage.Literals.TEXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTextvalue() {
		return textvalue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTextvalue(String newTextvalue) {
		String oldTextvalue = textvalue;
		textvalue = newTextvalue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PSMMPackage.TEXT__TEXTVALUE, oldTextvalue, textvalue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PSMMPackage.TEXT__TEXTVALUE:
				return getTextvalue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PSMMPackage.TEXT__TEXTVALUE:
				setTextvalue((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PSMMPackage.TEXT__TEXTVALUE:
				setTextvalue(TEXTVALUE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PSMMPackage.TEXT__TEXTVALUE:
				return TEXTVALUE_EDEFAULT == null ? textvalue != null : !TEXTVALUE_EDEFAULT.equals(textvalue);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (textvalue: ");
		result.append(textvalue);
		result.append(')');
		return result.toString();
	}

} //TextImpl
