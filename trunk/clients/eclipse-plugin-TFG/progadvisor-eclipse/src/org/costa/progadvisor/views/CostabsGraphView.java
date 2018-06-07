/**
Copyright (C) 2010  E.Albert, P.Arenas, S.Genaim, G.Puebla, and D.Zanardini
                    https://costa.ls.fi.upm.es

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
package org.costa.progadvisor.views;

import org.costa.progadvisor.awt.ScrollableSwingComposite;
import org.costa.progadvisor.panels.CostabsGraphPanel;
import org.costa.progadvisor.trackers.OutputManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
/**
 * Costa view to show the costa markers
 * @author groman
 *
 */
public class CostabsGraphView extends ViewPart {

	public CostabsGraphView () {

	}

	@Override
	public void createPartControl(Composite parent) {
		//CostabsGraphFrame embeddedComposite = new CostabsGraphFrame(parent, SWT.NONE);

		//		EmbeddedSwingComposite embeddedComposite = new EmbeddedSwingComposite(parent, SWT.NONE) {
		//			protected JComponent createSwingComponent() {
		//				JScrollPane scrollPane = new JScrollPane();
		//				JPanel panel = new CostabsGraphPanel ();
		//				CostabsGraphManager.getInstance().setScroll(scrollPane);
		//				scrollPane.setViewportView(panel);
		//				return scrollPane;
		//			}
		//		}; 
		//		embeddedComposite.populate();
		//		CostabsGraphManager.getInstance().setFrame(embeddedComposite);

//		EmbeddedSwingComposite embeddedComposite = new EmbeddedSwingComposite(parent, SWT.NONE) {
//			protected JComponent createSwingComponent() {
//				//GraphPanelHandler panel = new GraphPanelHandler (this);
//				//OutputManager.getInstance().setGraphPanel(panel);
//
//				CostabsGraphPanel panel = new CostabsGraphPanel(null);
//				OutputManager.getInstance().setPanel(panel);
//				return panel;
//			}
//		};
//		embeddedComposite.populate();

		ScrollableSwingComposite scroll = new ScrollableSwingComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL);
		CostabsGraphPanel panel = new CostabsGraphPanel(null);
		OutputManager.getInstance().setPanel(panel);
		OutputManager.getInstance().setScroll(scroll);
		scroll.setViewportView(panel);
	
	}

	@Override
	public void setFocus() {
	}

}