package br.pucrs.nomeusuario.exemplo.gui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Exemplo de múltiplas telas")
@Route("")
public class MainView extends VerticalLayout {
   public MainView() {
      Button tela1Button = new Button("Cadastro de pessoas");
      Button tela2Button = new Button("Edição de pessoas");
      tela1Button.addClickListener(e -> UI.getCurrent().navigate("tela1"));
      tela2Button.addClickListener(e -> UI.getCurrent().navigate("tela2"));
      add(tela1Button);
      add(tela2Button);
   }
}
