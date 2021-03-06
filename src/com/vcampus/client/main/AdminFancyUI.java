package com.vcampus.client.main;

/*
 * This file is *adopted from* part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

import com.alee.api.annotations.NotNull;
import com.alee.api.data.BoxOrientation;
import com.alee.api.data.CompassDirection;
import com.alee.api.jdk.SerializableSupplier;
import com.alee.api.version.Version;
import com.alee.demo.content.ExamplesManager;
import com.alee.demo.skin.decoration.FeatureStateBackground;
import com.alee.extended.behavior.ComponentResizeBehavior;
import com.alee.extended.canvas.WebCanvas;
import com.alee.extended.dock.SidebarButtonVisibility;
import com.alee.extended.dock.WebDockableFrame;
import com.alee.extended.dock.WebDockablePane;
import com.alee.extended.label.WebStyledLabel;
import com.alee.extended.memorybar.WebMemoryBar;
import com.alee.extended.overlay.AlignedOverlay;
import com.alee.extended.overlay.WebOverlay;
import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.GroupingType;
import com.alee.extended.statusbar.WebStatusBar;
import com.alee.extended.tab.DocumentAdapter;
import com.alee.extended.tab.DocumentData;
import com.alee.extended.tab.PaneData;
import com.alee.extended.tab.WebDocumentPane;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.desktoppane.WebDesktopPane;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WindowState;
import com.alee.laf.scroll.ScrollPaneState;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.separator.WebSeparator;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.laf.toolbar.WebToolBar;
import com.alee.laf.tree.TreeSelectionStyle;
import com.alee.laf.tree.WebTree;
import com.alee.laf.window.WebFrame;
import com.alee.managers.hotkey.Hotkey;
import com.alee.managers.language.LM;
import com.alee.managers.language.LanguageLocaleUpdater;
import com.alee.managers.language.LanguageManager;
import com.alee.managers.notification.NotificationManager;
import com.alee.managers.settings.Configuration;
import com.alee.managers.style.*;
import com.alee.managers.task.TaskGroup;
import com.alee.managers.task.TaskManager;
import com.alee.skin.dark.WebDarkSkin;
import com.alee.utils.CollectionUtils;
import com.alee.utils.CoreSwingUtils;
import com.alee.utils.XmlUtils;
import com.alee.utils.swing.Customizer;
import com.alee.utils.swing.extensions.KeyEventRunnable;
import com.vcampus.UI.FancyUI;
import com.vcampus.client.main.manager.*;
import com.vcampus.client.main.shop.AppShop;
import com.vcampus.client.main.shop.AppShopAdmin;
import com.vcampus.server.AppAdminInfo;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * ??????????????????????????????????????????FancyUI
 *
 */


public final class AdminFancyUI extends FancyUI
{

    /**
     * Returns application instance.
     *
     * @return application instance
     */
    @NotNull
    protected static AdminFancyUI getInstance() {
        if ( instance == null )
        {
            instance = new AdminFancyUI();
        }
        return (AdminFancyUI) instance;   //polymorphism
    }

    public AdminFancyUI() {
        super();

        JInternalFrame internal = new JInternalFrame("???????????????",true,true,true, true);
        internal.setContentPane(new AppAdmin().getContentPane());
        internal.pack();
        internal.setVisible(true);
        internal.setBounds(-5,-5,1400,1000);
        internal.toFront();
        desktopPane.add(internal);

        /* Teachers need no chatroom
        final JInternalFrame chatFrame = new JInternalFrame("?????????",true,true,true, true);
        chatFrame.setContentPane(new AppChatDiag().getContentPane());
        chatFrame.pack();
        chatFrame.setVisible(true);
        chatFrame.setBounds(1000,700,300,500);
        desktopPane.add(chatFrame);
         */
    }

    @Override
    /**
     * Initializes categoryFrame.
     * Called during initializeDocks().
     *
     */
    protected void initializeCategoryFrame()
    {
        categoryFrame = new WebDockableFrame(StyleId.dockableframeCompact,"FancyUITest","vCampus");
        categoryFrame.setPosition(CompassDirection.west);

        //final WebTree appTree = new WebTree(getTreeNodes());
        WebTree appTree=  new WebTree(new ManCategory().getNode());
        appTree.setEditable(false);
        appTree.setRootVisible(false);
        appTree.setShowsRootHandles(true);
        /* WebTree methods*/
        appTree.setMultipleSelectionAllowed(false);
        appTree.setSelectionStyle(TreeSelectionStyle.line);
        appTree.expandAll();
        appTree.onKeyPress ( Hotkey.ENTER, new KeyEventRunnable()
        {
            @Override
            public void run ( @NotNull final KeyEvent e )
            {
                //TODO
                //open ( appTree.getSelectedNode () );
            }
        } );

        appTree.addTreeSelectionListener(new TreeSelectionListener() {

            /**
             * ???????????????????????????
             */
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                if (!appTree.isSelectionEmpty()) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) appTree.getLastSelectedPathComponent();
                    JInternalFrame internal = null;
                    switch((String)node.getUserObject()) {
                        case "????????????":
                            internal = new JInternalFrame("??????????????????",true,true,true, true);
                            internal.setContentPane(new AppAdminInfoFrame().getContentPane());
                            internal.pack();
                            internal.setVisible(true);
                            internal.setSize(1000,1000);
                            internal.toFront();
                            desktopPane.add(internal);
                            break;
                        case "??????????????????":
                            internal = new JInternalFrame("??????????????????",true,true,true, true);
                            internal.setContentPane(new StuManage().getContentPane());
                            internal.pack();
                            internal.setVisible(true);
                            internal.setSize(1000,1000);
                            internal.toFront();
                            desktopPane.add(internal);
                            break;
                        case "?????????":
                            // fall through
                        case "??????????????????":
                            // fall through
                        case "????????????":
                            // fall through
                        case "????????????":
                            // fall through
                        case "????????????":
                            internal = new JInternalFrame("??????????????????",true,true,true, true);
                            internal.setContentPane(new AppShopAdmin().getContentPane());
                            internal.pack();
                            internal.setVisible(true);
                            internal.setSize(1500,1000);
                            internal.toFront();
                            desktopPane.add(internal);
                            break;
                        default:
                            //System.out.println("Nothing selected");
                            break;
                    }
                }
            }
        });
        final WebScrollPane appTreeScroll = new WebScrollPane ( StyleId.scrollpaneTransparentHoveringExtending, appTree );
        appTreeScroll.registerSettings ( new Configuration<ScrollPaneState> ( "ExamplesScroll" ) );

        // Filtering field
        // not implemented - requires tree models that implement FilterableNodes e.g. WebExTree
        // final WebTreeFilterField filter = new WebTreeFilterField ( appTree );

        // Frame UI composition
        final WebSeparator separator = new WebSeparator ( StyleId.separatorHorizontal );
        //categoryFrame.add ( new GroupPanel( GroupingType.fillLast, 0, false, filter, separator, appTreeScroll ) );
        categoryFrame.add ( new GroupPanel( GroupingType.fillLast, 0, false, appTreeScroll ) );

    }

}