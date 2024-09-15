const url = "http://localhost:8080/task/user/3";

function hideLoader() {
    document.getElementById("loading").style.display = "none";
}

function show(tasks) {
    let tab = `
        <thead>
            <th scope="col">#</th>
            <th scope="col">Description</th>
            <th scope="col">Username</th>
            <th scope="col">User Id</th>
        </thead>`;
    
    for (let task of tasks) {
        let username = task.user ? task.user.username : "N/A";  // Verifica se 'user' existe
        let userId = task.user ? task.user.id : "N/A";  // Verifica se 'user' existe
        
        tab += `
        <tr>
            <td scope="row">${task.id}</td>
            <td>${task.description}</td>
            <td>${username}</td>
            <td>${userId}</td>
        </tr>`;
    }

    document.getElementById("tasks").innerHTML = tab;
}

async function getAPI(url) {
    try {
        const response = await fetch(url, { method: "GET" });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();
        console.log(data);
        hideLoader();
        show(data);
    } catch (error) {
        console.error("Failed to fetch tasks:", error);
        hideLoader();
    }
}

getAPI(url);
