
///////////////////////////////////////////////////////////////////////////////////////////////
// power-framework: Java library with many purposes of usage.
// Copyright (C) 2016-2022 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation;
// version 2.1 of the License only.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
///////////////////////////////////////////////////////////////////////////////////////////////

package org.nanoboot.powerframework.view.boxes;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.nanoboot.powerframework.view.View;
import org.nanoboot.powerframework.view.window.controls.Button;

/**
 * Represents a message box- a window used to inform user about something.
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */

public class MessageBox extends Box {

    /**
     *
     * @param titleText
     * @param text
     */
    public static void showBox(String titleText,
            String text) {
        MessageBox messageBox = new MessageBox(titleText, text);
        messageBox.showAndWait();
    }

    private MessageBox(String titleText,
            String text) {
        super(titleText, text);
    }

    @Override
    void initBox() {
        circle.setFill(Color.rgb(114, 159, 207));
        Rectangle rectangle1 = new Rectangle(2 * View.getDpmm(), 5 * View.getDpmm());
        Rectangle rectangle2 = new Rectangle(2 * View.getDpmm(), 2 * View.getDpmm());
        this.icon2.getChildren().addAll(rectangle1, rectangle2);
        this.icon.getChildren().add(icon2);

        StackPane.setAlignment(rectangle1, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(rectangle2, Pos.TOP_CENTER);
        this.icon2.setMaxHeight(8 * View.getDpmm());
        rectangle1.setFill(Color.rgb(238, 238, 238));

        rectangle2.setFill(Color.rgb(238, 238, 238));
        Button okButton = new Button("OK");

        this.placeForButtons.getChildren().add(okButton);
        okButton.setMaxWidth(20 * View.getDpmm());
        okButton.setOnAction(this::handleOKButtonAction);
    }
}
