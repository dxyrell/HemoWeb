function abrirModal(option) {
    const dialog = document.querySelector("#" + option + "Modal");
    dialog.showModal();
    zerarInputs();
}

function fecharModal(option) {
    const dialog = document.querySelector("#" + option + "Modal");
    dialog.close();
}

async function carregarUsuarios() {
    // Carrega a lista de usuários
    htmx.ajax('GET', 'http://localhost:8080/pages/fragment/lista-usuarios', {
        target: '#usuarios-container'
    });
}

async function adicionarUsuario() {
    // Captura os dados do formulário de adição
    const data = {
        cpf: document.querySelector("#cpfInput").value,
        nome: document.querySelector("#nomeInput").value,
        email: document.querySelector("#emailInput").value,
        senha: document.querySelector("#senhaInput").value,
        cargo: document.querySelector("#cargoInput").value
    };

    try {
        // Envia os dados para o backend
        const response = await fetch("http://localhost:8080/usuarios", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error("Erro ao adicionar usuário.");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
    }

    fecharModal('add');
    await carregarUsuarios();
}

async function deletarUsuario() {
    // Captura o CPF do usuário a ser deletado
    const cpf = document.querySelector("#deleteCpfInput").value;

    try {
        const response = await fetch(`http://localhost:8080/usuarios/${cpf}`, {
            method: "DELETE"
        });

        if (!response.ok) {
            throw new Error("Erro ao deletar usuário.");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
    }

    fecharModal('delete');
    await carregarUsuarios();
}

async function editarUsuario() {
    // Captura os dados do formulário de edição
    const cpf = document.querySelector("#editCpfInput").value;
    const data = {
        nome: document.querySelector("#editNomeInput").value,
        email: document.querySelector("#editEmailInput").value,
        cargo: document.querySelector("#editCargoInput").value
    };

    try {
        const response = await fetch(`http://localhost:8080/usuarios/${cpf}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error("Erro ao editar usuário.");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
    }

    fecharModal('edit');
    await carregarUsuarios();
}

function zerarInputs() {
    // Limpa todos os inputs dos modais
    const todosInputs = document.querySelectorAll("input, select");

    todosInputs.forEach((input) => {
        input.value = "";
    });
}
