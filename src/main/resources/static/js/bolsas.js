function abrirModal(option) {
    const dialog = document.querySelector("#" + option + "Modal");
    dialog.showModal();
    zerarInputs();
}

function fecharModal(option) {
    const dialog = document.querySelector("#" + option + "Modal");
    dialog.close();
}

async function carregarBolsas() {
    // Carrega a lista de bolsas
    htmx.ajax('GET', 'http://localhost:8080/pages/fragment/lista-bolsas', {
        target: '#bolsas-container'
    });
}

async function adicionarBolsa() {
    // Captura os dados do formulário de adição
    const data = {
        id: document.querySelector("#idInput").value,
        status: document.querySelector("#statusInput").value,
        data: document.querySelector("#dataInput").value,
        volume: parseFloat(document.querySelector("#volumeInput").value),
        local: document.querySelector("#localInput").value,
        cpfDoador: document.querySelector("#cpfDoadorInput").value
    };

    try {
        const response = await fetch("http://localhost:8080/bolsas", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error("Erro ao adicionar bolsa.");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
    }

    fecharModal('add');
    await carregarBolsas();
}

async function deletarBolsa() {
    const id = document.querySelector("#deleteIdInput").value;

    try {
        const response = await fetch(`http://localhost:8080/bolsas/${id}`, {
            method: "DELETE"
        });

        if (!response.ok) {
            throw new Error("Erro ao deletar bolsa.");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
    }

    fecharModal('delete');
    await carregarBolsas();
}

async function editarBolsa() {
    const id = document.querySelector("#editIdInput").value;
    const data = {
        status: document.querySelector("#editStatusInput").value,
        data: document.querySelector("#editDataInput").value,
        volume: parseFloat(document.querySelector("#editVolumeInput").value),
        local: document.querySelector("#editLocalInput").value,
        cpfDoador: document.querySelector("#editCpfDoadorInput").value
    };

    try {
        const response = await fetch(`http://localhost:8080/bolsas/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error("Erro ao editar bolsa.");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
    }

    fecharModal('edit');
    await carregarBolsas();
}

function zerarInputs() {
    const todosInputs = document.querySelectorAll("input");
    todosInputs.forEach((input) => {
        input.value = "";
        if (input.type === "checkbox") input.checked = false;
    });
}
