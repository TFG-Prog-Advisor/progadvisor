package org.costa.progadvisor.rulers;

import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.texteditor.rulers.AbstractContributedRulerColumn;


public class CostabsResultsRuler extends AbstractContributedRulerColumn {
	private CostabsLineNumberColumn delegate = new CostabsLineNumberColumn();

	public CostabsResultsRuler() {
	}

	@Override
	public void setModel(IAnnotationModel model) {
		delegate.setModel(model);
	}

	@Override
	public void redraw() {
		delegate.redraw();
	}

	@Override
	public Control createControl(CompositeRuler parentRuler, Composite parentControl) {
		return delegate.createControl(parentRuler, parentControl);
	}

	@Override
	public Control getControl() {
		return delegate.getControl();
	}

	@Override
	public int getWidth() {
		return delegate.getWidth();
	}

	@Override
	public void setFont(Font font) {
		delegate.setFont(font);
	}

}
