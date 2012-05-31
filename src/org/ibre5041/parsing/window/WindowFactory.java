package org.ibre5041.parsing.window;

import java.io.File;
import java.io.IOException;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;
import org.ibre5041.parsing.PBMLexer;
import org.ibre5041.parsing.PBMParser;
import org.ibre5041.parsing.utils.ANTLRNoCaseFileStream;

import com.trolltech.qt.core.QFileInfo;

public class WindowFactory {
	private static final WindowFactory instance = new WindowFactory();

	public static WindowFactory getInstance() {
		return instance;
	}

	public PBFile createWindow(File filename) throws IOException, RecognitionException {
		QFileInfo f = new QFileInfo(filename.getName());

		if (!filename.isFile()) {
			throw new RuntimeException("File does not exist: " + filename);		
		}

		Tree AST = parse(filename);
		
		PBFile window = null;		
		switch (WindowTypeEnum.enumFromSuffix(f.suffix())) {
		case APP:
			window = new AppWindow();			
			break;
		case DATA_WINDOW:
			window = new DataWindow();
			break;
		default:
			return null;
			//throw new RuntimeException("Unsupported file suffix: " + file.suffix());
		}

		window.setAST(AST);
		window.setFilename(filename);
		return window;
	}

	private final Tree parse(File filename) throws IOException,
			RecognitionException {
		try {
			PBMLexer lex = new PBMLexer(new ANTLRNoCaseFileStream(filename.getAbsolutePath()));
			CommonTokenStream tokens = new CommonTokenStream(lex);
			PBMParser parser = new PBMParser(tokens);

			PBMParser.start_rule_return AST = parser.start_rule();

			System.err.println(filename + ": "
					+ parser.getNumberOfSyntaxErrors());

			if (parser.getNumberOfSyntaxErrors() != 0) {
				// TODO THROW SOMETHING HERE
				System.exit(1);
			}

			Tree t = (Tree) AST.getTree();
			CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
			nodes.setTokenStream(tokens);

			// Object o = nodes.nextElement();
			// while (!nodes.isEOF(o)) {
			// o = nodes.nextElement();
			// Tree tt = (Tree) o;
			// if (tt.getType() == PBMParser.SQLSTATEMENT) {
			// System.out.println(tt.toStringTree());
			// }
			// }
			return t;
		}
		// } catch (RecognitionException e) {
		// System.err.println(e.toString());
		// } catch (IOException e) {
		// System.err.println(e.toString());
		// } catch (java.lang.OutOfMemoryError e) {
		// System.err.println(filename + ":");
		// System.err.println(e.toString());
		finally {
		}
	}
}
