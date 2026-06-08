package br.pucrs.nomeusuario.exemplo.gui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import br.pucrs.nomeusuario.exemplo.dados.CadastroPessoas;
import br.pucrs.nomeusuario.exemplo.dados.Pessoa;

@PageTitle("Tela 3 - Remover cadastro de pessoas - Remocao")
@Route("tela3")
public class Tela3View extends VerticalLayout {
    // Instância do cadastro de pessoas
    private final CadastroPessoas cadPessoas;
    // Campos do formulário
    private final TextField nome;
    private final TextField email;
    private final DatePicker dataNascimento;
    private final ComboBox<String> pais;
    private final Checkbox aceitaTermos;
    // Grid para exibir as pessoas
    private final Grid<Pessoa> grid; 

    public Tela3View() {
        // Inicializando o cadastro de pessoas
        cadPessoas = CadastroPessoas.getInstance();

        // Inicializando os campos do formulário
        nome = new TextField("Nome");
        email = new TextField("E-mail");
        dataNascimento = new DatePicker("Data de nascimento");
        pais = new ComboBox<>("País");
        pais.setItems("Brasil", "Portugal", "EUA", "Inglaterra");

        aceitaTermos = new Checkbox("Aceito os termos de serviço");
        // inicializando o Grid para exibir as pessoas
        grid = new Grid<>(Pessoa.class);

        // Definindo as características do layout básico
        setSpacing(true);
        setPadding(true);

        // Define título do formulário
        add(new H2("Tela 3 - Remover cadastro de pessoas - Remocao"));
    
        // Definicoes de botoes de acao 
        Button deleteButton = new Button("Deletar", VaadinIcon.CHECK.create());
        deleteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteButton.addClickShortcut(Key.ENTER);

        // Define o botão de retorno à página principal
        Button backButton = new Button("Voltar");
        backButton.addClickListener(e -> UI.getCurrent().navigate(""));
        add(backButton);

    }
}
