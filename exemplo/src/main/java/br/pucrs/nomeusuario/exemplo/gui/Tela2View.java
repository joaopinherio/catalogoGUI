package br.pucrs.nomeusuario.exemplo.gui;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import br.pucrs.nomeusuario.exemplo.dados.CadastroPessoas;
import br.pucrs.nomeusuario.exemplo.dados.Pessoa;

@PageTitle("Tela 2 - Cadastro de pessoas - Edição")
@Route("tela2")
public class Tela2View extends VerticalLayout {
    // Instância do cadastro de pessoas
    private final CadastroPessoas cadPessoas;
    // Campos do formulário
    private final TextField nome;
    private final TextField email;
    private final DatePicker dataNascimento;
    private final ComboBox<String> pais;
    // Botoes
    private final Button salvarButton;
    private final Button cancelarButton;
    // Grid para exibir as pessoas
    private final Grid<Pessoa> grid;
    // Referencia para a pessoa selecionada
    private Pessoa pessoaSelecionada;

    public Tela2View() {
        // Inicializando o cadastro de pessoas
        cadPessoas = CadastroPessoas.getInstance();
        // Inicializando os campos do formulário
        nome = new TextField("Nome");
        nome.setReadOnly(true); // O nome não pode ser atualizado
        email = new TextField("E-mail");
        dataNascimento = new DatePicker("Data de nascimento");
        pais = new ComboBox<>("País");
        pais.setItems("Brasil", "Portugal", "EUA", "Inglaterra");
        // inicializando o Grid para exibir as pessoas
        grid = new Grid<>(Pessoa.class);

        // Definindo as características do layout básico
        setSpacing(true);
        setPadding(true);

        // Define título do formulário
        add(new H2("Tela 2 - Cadastro de pessoas - EDIÇÃO"));

        // Configuração do formulário
        FormLayout formLayout = new FormLayout(nome, email, dataNascimento, pais);

        // Definição dos botões de ação
        salvarButton = new Button("Atualizar", VaadinIcon.CHECK.create());
        salvarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        salvarButton.addClickShortcut(Key.ENTER);
        salvarButton.addClickListener(click -> this.atualizarFormulario());

        cancelarButton = new Button("Cancelar");
        Dialog dialogoCancelamento = criaDialogoDeCancelamento();
        cancelarButton.addClickListener(click -> dialogoCancelamento.open());

        // Adiciona botoes de ação em um layout horizontal
        HorizontalLayout botoesLayout = new HorizontalLayout(salvarButton, cancelarButton);

        // Configuração do Grid
        grid.setItems(cadPessoas.getLista());
        grid.setColumns("nome", "email", "pais", "dataNascimento");
        grid.asSingleSelect().addValueChangeListener(event -> preparaEdicaoPessoa(event));

        // Monta todos os elementos na janela
        add(formLayout, botoesLayout, new H2("Usuários Cadastrados"), grid);
        add(new Hr());

        // Define o botão de retorno à página principal
        Button backButton = new Button("Voltar");
        backButton.addClickListener(e -> UI.getCurrent().navigate(""));
        add(backButton);

        // deixa formulário desabilitado no início
        habilitarFormulario(false);

    }

    // Atualiza a pessoa selecionada
    private void atualizarFormulario() {
        if (nome.getValue().equals("") || email.getValue().equals("") ||
                pais.getValue() == null || dataNascimento.getValue() == null) {
            Notification.show("Erro! Campo vazio.", 3000, Notification.Position.BOTTOM_STRETCH);
        } else {
            Pessoa p = new Pessoa(nome.getValue(),
                    email.getValue(),
                    pais.getValue(),
                    dataNascimento.getValue());

            cadPessoas.update(pessoaSelecionada.getID(), p);

            String mensagem = "Usuário " + p.getNome() + " atualizado com sucesso!";
            Notification.show(mensagem, 3000, Notification.Position.BOTTOM_STRETCH);
        }
        grid.getDataProvider().refreshAll();
        limparFormulario();
        habilitarFormulario(false); // Desabilita o form após salvar
    }

    // Preenche o formulário a partir do grid
    private void preencherFormulario(Pessoa pessoa) {
        nome.setValue(pessoa.getNome());
        email.setValue(pessoa.getEmail());
        dataNascimento.setValue(pessoa.getDataNascimento());
        pais.setValue(pessoa.getPais());
    }

    // Habilitar/desabilitar os campos do formulário
    private void habilitarFormulario(boolean opcao) {
        // 'nome' é readonly, então não mexemos no 'enabled' dele
        email.setEnabled(opcao);
        dataNascimento.setEnabled(opcao);
        pais.setEnabled(opcao);
        salvarButton.setEnabled(opcao);
        cancelarButton.setEnabled(opcao);
    }

    private void preparaEdicaoPessoa(ComponentValueChangeEvent<Grid<Pessoa>, Pessoa> event) {
        pessoaSelecionada = event.getValue();

        if (pessoaSelecionada != null) {
            // Se uma pessoa foi selecionada, preenche o formulário
            preencherFormulario(pessoaSelecionada);
            habilitarFormulario(true);
        } else {
            // Se a seleção foi limpa, limpa o formulário
            limparFormulario();
            habilitarFormulario(false);
        }
    }

    // Limpa seleção do grid
    private void limparFormulario() {
        // Desseleciona qualquer item na grid (isso evita loops de eventos)
        grid.asSingleSelect().clear();
        // Limpa os campos
        nome.clear();
        dataNascimento.clear();
        pais.clear();
        email.clear();
        // Coloca o foco no campo nome
        nome.focus();
    }

    private Dialog criaDialogoDeCancelamento() {
        Dialog dialogo = new Dialog();
        dialogo.setHeaderTitle("Confirmar cancelamento");
        dialogo.add(new Paragraph("Você tem certeza que deseja cancelar e limpar o formulário?"));
        Button confirmarCancelamento = new Button("Sim, cancelar", e -> {
            limparFormulario();
            dialogo.close();
        });
        confirmarCancelamento.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        Button fecharDialogo = new Button("Não", e -> dialogo.close());
        dialogo.getFooter().add(fecharDialogo, confirmarCancelamento);
        return dialogo;
    }
}