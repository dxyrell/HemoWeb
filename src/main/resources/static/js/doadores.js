function abrirModal(option) {
    const dialog = document.querySelector("#" + option + "Modal");
    dialog.showModal();
    zerarInputs();
}

function fecharModal(option) {
    const dialog = document.querySelector("#" + option + "Modal");
    dialog.close();
}

async function carregarDoadores() {
    // Carrega a lista de doadores
    htmx.ajax('GET', 'http://localhost:8080/pages/fragment/lista-doadores', {
        target: '#doadores-container'
    });
}

async function adicionarDoador() {
    // Captura os dados do formulário de adição
    const data = {
        cpf: document.querySelector("#cpfInput").value,
        nome: document.querySelector("#nomeInput").value,
        email: document.querySelector("#emailInput").value,
        telefone: document.querySelector("#telefoneInput").value,
        endereco: document.querySelector("#enderecoInput").value,
        nascimento: document.querySelector("#nascimentoInput").value,
        tipoSanguineo: document.querySelector("#tipoSanguineoInput").value
    };

    try {
        const response = await fetch("http://localhost:8080/doadores", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error("Erro ao adicionar doador.");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
    }

    fecharModal('add');
    await carregarDoadores();
}

async function deletarDoador() {
    const cpf = document.querySelector("#deleteCpfInput").value;

    try {
        const response = await fetch(`http://localhost:8080/doadores/${cpf}`, {
            method: "DELETE"
        });

        if (!response.ok) {
            throw new Error("Erro ao deletar doador.");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
    }

    fecharModal('delete');
    await carregarDoadores();
}

async function editarDoador() {
    const cpf = document.querySelector("#editCpfInput").value;
    const data = {
        nome: document.querySelector("#editNomeInput").value,
        email: document.querySelector("#editEmailInput").value,
        telefone: document.querySelector("#editTelefoneInput").value,
        endereco: document.querySelector("#editEnderecoInput").value,
        nascimento: document.querySelector("#editNascimentoInput").value,
        tipoSanguineo: document.querySelector("#editTipoSanguineoInput").value
    };

    try {
        const response = await fetch(`http://localhost:8080/doadores/${cpf}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error("Erro ao editar doador.");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
    }

    fecharModal('edit');
    await carregarDoadores();
}

function zerarInputs() {
    const todosInputs = document.querySelectorAll("input");
    todosInputs.forEach((input) => {
        input.value = "";
    });
}
