import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class DoubleTextField extends JTextField {
    private boolean isInteger;

    public DoubleTextField(boolean isInteger) {
        this.isInteger = isInteger;
        setDocument(new NumericDocument());
    }

    private class NumericDocument extends PlainDocument {
        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;

            if (isInteger) {
                try {
                    Integer.parseInt(str);
                    super.insertString(offset, str, attr);
                } catch (NumberFormatException e) {
                    // Ignoriere ungültige Eingabe
                }
            } else {
                String currentText = getText(0, getLength());
                String newText = currentText.substring(0, offset) + str + currentText.substring(offset);

                // Ersetze alle Kommas durch Punkte
                newText = newText.replace(",", ".");

                try {
                    Double.parseDouble(newText);
                    super.insertString(offset, str, attr);
                } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    // Ignoriere ungültige Eingabe
                }
            }
        }
    }
}
