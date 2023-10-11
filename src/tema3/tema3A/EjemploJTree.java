package tema3.tema3A;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import java.io.File;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * Este ejemplo muestra el uso del componente JTree.
 * Utiliza la estructura de directorios y ficheros
 * del directorio actual como nodos del árbol
 * Configura un renderer para poner iconos distintos
 * a los directorios y a los ficheros.
 */
public class EjemploJTree extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EjemploJTree() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ejemplo Swing");

        DefaultMutableTreeNode root = createNodes(new File("."));

        JTree jTree = new JTree(root);

        // renderer utilizado para los iconos de los nodos
        // intermedios (directorios) y de los nodos hoja (ficheros)
        ImageIcon directoryIcon = new ImageIcon(getClass().getResource("/coffee-icon-16.png"));
        ImageIcon fileIcon = new ImageIcon(getClass().getResource("/pingus-icon-16.png"));
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setLeafIcon(fileIcon);
        renderer.setOpenIcon(directoryIcon);
        renderer.setClosedIcon(directoryIcon);
        jTree.setCellRenderer(renderer);

        // el árbol se añade a un panel de scroll
        JScrollPane scrollPane = new JScrollPane(jTree);

        // se cambia el tamaño preferido del árbol
        jTree.setPreferredSize(new Dimension(120, 200));

        add(scrollPane, BorderLayout.CENTER);
        
        pack();
        setVisible(true);
    }

    private void processDir(DefaultMutableTreeNode node, File dir) {
        for (File file : dir.listFiles()) {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(file.toString());
            node.add(child);
            if (file.isDirectory())
                processDir(child, file);
        }
    }

    private DefaultMutableTreeNode createNodes(File rootFile) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootFile.toString());
        processDir(root, rootFile);
        return root;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new EjemploJTree();
            }

        });
    }
}
