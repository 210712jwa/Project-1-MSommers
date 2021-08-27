let logoutButton = document.getElementById('logout');

let typeInput = document.getElementById('type');

let statusInput = document.getElementById('status');

let newStatusInput = document.getElementById('changestatus');

let idInput = document.getElementById('reimIDInput');

let newTypeInput = document.getElementById('newType');

const logout = (event) => {
    event.preventDefault(); 

    fetch('http://localhost:7000/logout', {
        'method': 'POST',
        'credentials': 'include',
    }).then((response) => {
        if(response.status === 200){
            window.location.href = '/index.html';
        }
    })
};



const newStatusButton = (event) => {
    event.preventDefault(); 


    fetch('http://localhost:7000/currentuser', {
        'method': 'GET',
        'credentials': 'include',
    }).then((response) => {
        if(response.status === 401){
            window.location.href = '/index.html'
        } 
        else if (response.status === 200){
            return response.json();
        }
    }).then((user) => {

        if(user.userRole.uID != 2){
            window.location.href = '/index.html'
        };

        // let statusInfo1 = {"reimStatus": 1};
        // let statusInfo2 = {"reimStatus": 2};
        // let statusInfo3 = {"reimStatus": 3};


        if(newTypeInput.value == "aproved"){
            let statusInfo ={"reimStatus": 1};

            return fetch(`http://localhost:7000/user/${user.uID}/editReimbursement/${idInput.value}`, {
            'method': 'PUT',
            'credentials': 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(statusInfo)
        });
        } else if(newTypeInput.value == "denied"){
            let statusInfo ={"reimStatus": 2};

            return fetch(`http://localhost:7000/user/${user.uID}/editReimbursement/${idInput.value}`, {
            'method': 'PUT',
            'credentials': 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(statusInfo)
            });
        } else if(newTypeInput.value == "pending"){
            let statusInfo ={"reimStatus": 3};

            return fetch(`http://localhost:7000/user/${user.uID}/editReimbursement/${idInput.value}`, {
            'method': 'PUT',
            'credentials': 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(statusInfo)
            });
        }

    })
    // window.location.href = '/admin.html'
};



const statusButton = (event) => {
    event.preventDefault(); 


    fetch('http://localhost:7000/currentuser', {
        'method': 'GET',
        'credentials': 'include',
    }).then((response) => {
        if(response.status === 401){
            window.location.href = '/index.html'
        } 
        else if (response.status === 200){
            return response.json();
        }
    }).then((user) => {

        if(user.userRole.uID != 2){
            window.location.href = '/index.html'
        };

        if(typeInput.value == "all"){

            var Table = document.getElementById("innerTable");
            Table.innerHTML = "";


        return fetch(`http://localhost:7000/reimbursements`, {
        'method': 'GET',
        'credentials': 'include'
        });

        } else if(typeInput.value == "aproved"){

            var Table = document.getElementById("innerTable");
            Table.innerHTML = "";

        return fetch(`http://localhost:7000/filterByStatus/1`, {
        'method': 'GET',
        'credentials': 'include'
        });
        } else if(typeInput.value == "denied"){

            var Table = document.getElementById("innerTable");
            Table.innerHTML = "";

            return fetch(`http://localhost:7000/filterByStatus/2`, {
            'method': 'GET',
            'credentials': 'include'
            });
        } else if(typeInput.value == "pending"){

            var Table = document.getElementById("innerTable");
            Table.innerHTML = "";

            return fetch(`http://localhost:7000/filterByStatus/3`, {
            'method': 'GET',
            'credentials': 'include'
            });
        }

    }).then((response) => {
       return response.json()
    }).then((reimbursements) =>{
        populateReimbursements(reimbursements)
    })
};

function onLoad(event){
    fetch('http://localhost:7000/currentuser', {
        'method': 'GET',
        'credentials': 'include',
    }).then((response) => {
        if(response.status === 401){
            window.location.href = '/index.html'
        } 
        else if (response.status === 200){
            return response.json();
        }
    }).then((user) => {

        if(user.userRole.uID != 2){
            window.location.href = '/index.html'
        };

        if(typeInput.value == "all"){

        return fetch(`http://localhost:7000/reimbursements`, {
        'method': 'GET',
        'credentials': 'include'
        });

        } else if(typeInput.value == "aproved"){

        return fetch(`http://localhost:7000/filterByStatus/1`, {
        'method': 'GET',
        'credentials': 'include'
        });
        } else if(typeInput.value == "denied"){

            return fetch(`http://localhost:7000/filterByStatus/2`, {
            'method': 'GET',
            'credentials': 'include'
            });
        } else if(typeInput.value == "pending"){

            return fetch(`http://localhost:7000/filterByStatus/3`, {
            'method': 'GET',
            'credentials': 'include'
            });
        }

    }).then((response) => {
       return response.json()
    }).then((reimbursements) =>{
        populateReimbursements(reimbursements)
    })
}


function populateReimbursements(reimArray){

    let tbody = document.querySelector('#reimbursements tbody'); 

    for (const reim of reimArray){
       let tr = document.createElement('tr');
       
       let reimID = document.createElement('td');
       reimID.innerHTML = reim.rID;

       let reimType = document.createElement('td');
       reimType.innerHTML = reim.rType.rType;

       let reimDate = document.createElement('td');
       reimDate.innerHTML = reim.rSub;

       let reimAmount = document.createElement('td');
       reimAmount.innerHTML = reim.rAmount;

       let reimDesc = document.createElement('td');
       reimDesc.innerHTML = reim.desc;

       let reimStatus = document.createElement('td');
       reimStatus.innerHTML = reim.rStatus.rStatus;
      
        tr.appendChild(reimID);
        tr.appendChild(reimType);
        tr.appendChild(reimDate);
        tr.appendChild(reimAmount);
        tr.appendChild(reimDesc);
        tr.appendChild(reimStatus);
        

        tbody.appendChild(tr);
    }
}

logoutButton.addEventListener('click', logout);
window.addEventListener('load', onLoad)
statusInput.addEventListener('click', statusButton)

newStatusInput.addEventListener('click', newStatusButton)