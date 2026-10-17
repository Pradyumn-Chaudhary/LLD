package SOLID;

import java.util.ArrayList;
import java.util.List;

// ─────────────────────────────────────────────────────────────────────────────
// SOLID JUSTIFICATION:
//   S — SRP  : Each class has one job. Document manages elements; Persistence
//              handles storage; DocumentEditor orchestrates. (Deliberate tradeoff:
//              render() lives inside each element to honour OCP — see below.)
//   O — OCP  : Add a new element type (e.g. VideoElement) by implementing
//              DocumentElement — zero changes to Document or DocumentEditor.
//   L — LSP  : TextElement and ImageElement are fully substitutable wherever
//              DocumentElement is expected. SaveToFile/SaveToDB same for Persistence.
//   I — ISP  : DocumentElement exposes only render(). Persistence exposes only save().
//              No fat interfaces forcing unused method implementations.
//   D — DIP  : DocumentEditor depends on the Persistence abstraction, not on
//              SaveToFile or SaveToDB. Concrete strategy injected via constructor.
// ─────────────────────────────────────────────────────────────────────────────


// ISP: Minimal interface — only what every element must do.
interface DocumentElement {
    void render();
}


// OCP + LSP: New element types extend behaviour without modifying existing code.
class TextElement implements DocumentElement {
    private String content;

    public TextElement(String content) {
        this.content = content;
    }

    @Override
    public void render() {
        System.out.println("[TEXT]  " + content);
    }
}


// OCP + LSP: Fully substitutable for DocumentElement.
class ImageElement implements DocumentElement {
    private String sourcePath;

    public ImageElement(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    @Override
    public void render() {
        System.out.println("[IMAGE] " + sourcePath);
    }
}


// SRP: Only responsibility is managing its elements and rendering them.
// Composition (*--): Document owns the element list — elements die with the document.
class Document {
    private List<DocumentElement> elements;

    public Document() {
        this.elements = new ArrayList<>();
    }

    // OCP: Iterates polymorphically — adding new element types needs no change here.
    public void renderDocument() {
        System.out.println("=== Rendering Document ===");
        for (DocumentElement el : elements) {
            el.render();
        }
        System.out.println("==========================");
    }

    public void addElement(DocumentElement el) {
        elements.add(el);
        System.out.println("[Document] Added: " + el.getClass().getSimpleName());
    }

    public void removeElement(DocumentElement el) {
        if (elements.remove(el)) {
            System.out.println("[Document] Removed: " + el.getClass().getSimpleName());
        } else {
            System.out.println("[Document] Element not found.");
        }
    }
}


// DIP abstraction: DocumentEditor depends on this, not on concrete save classes.
// ISP: Single focused method — nothing more.
// Uses (..>): save() receives Document as a parameter (temporary scope, not a field).
interface Persistence {
    void save(Document doc);
}


// LSP: Fully substitutable for Persistence.
class SaveToFile implements Persistence {
    @Override
    public void save(Document doc) {
        System.out.println("[SaveToFile] Document saved to file.");
    }
}


// LSP: Fully substitutable for Persistence.
class SaveToDB implements Persistence {
    @Override
    public void save(Document doc) {
        System.out.println("[SaveToDB]   Document saved to database.");
    }
}


// SRP: Single job — coordinate editing, rendering, and saving via delegation.
// DIP: Depends on Persistence abstraction; concrete strategy injected at construction.
// Aggregation (o--): Holds references to Document and Persistence but does not own them.
class DocumentEditor {
    private Document doc;
    private Persistence persist;

    // Constructor injection — satisfies DIP; strategy swappable without touching this class.
    public DocumentEditor(Document doc, Persistence persist) {
        this.doc     = doc;
        this.persist = persist;
    }

    public void renderDocument()          { doc.renderDocument(); }
    public void addElement(DocumentElement el)    { doc.addElement(el); }
    public void removeElement(DocumentElement el) { doc.removeElement(el); }

    // DIP in action: editor calls the abstraction; doesn't know or care about the implementation.
    public void save() { persist.save(doc); }
}


// ─────────────────────────────────────────────────────────────────────────────
// ENTRY POINT
// ─────────────────────────────────────────────────────────────────────────────
public class document_editor {

    public static void main(String[] args) {

        Document doc = new Document();

        // Swap SaveToFile <-> SaveToDB freely — DIP ensures DocumentEditor never changes.
        DocumentEditor editor = new DocumentEditor(doc, new SaveToFile());

        editor.addElement(new TextElement("My Document Title"));
        editor.addElement(new TextElement("Introduction paragraph..."));
        editor.addElement(new ImageElement("/assets/diagram.png"));

        editor.renderDocument();

        editor.removeElement(doc.equals(null) ? null : new TextElement("intro"));   // demo remove
        System.out.println();

        editor.renderDocument();
        editor.save();
    }
}
