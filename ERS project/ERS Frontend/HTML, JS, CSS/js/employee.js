let logoutButton = document.getElementById('logout');

let submitButton = document.getElementById('submit');

let dateInput = document.getElementById('date');
let amountInput = document.getElementById('amount');
let descInput = document.getElementById('desc');
let typeInput = document.getElementById('type');
//const currentUserObject;



function submit(event) {
    event.preventDefault(); // this will prevent the default behavior of what happens when 
    // a button inside a form element is clicked

    const subInfo = {
        'reimAmount': amountInput.value,
        'reimDesc': descInput.value,
        'reimType': typeInput.value,
        'rSub': dateInput.value
    };

    fetch('http://localhost:7000/currentuser', {
        'credentials': 'include',
        'method': 'GET'
    }).then((response) => {
        if (response.status === 401) {
            window.location.href = '/index.html'
        } else if (response.status === 200) {
            return response.json();
        }
    }).then((user) => {
        return fetch(`http://localhost:7000/user/${user.uID}/reimbursement`, {
            'method': 'POST', 
            'credentials': 'include',
            headers: {
                'Content-Type': 'application/json' // application/json is a MIME type
            },
            body: JSON.stringify(subInfo)
        });
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = '/employee.html';
        } 
    })
};



const logout = (event) => {
    event.preventDefault(); //prevents default behavior

    fetch('http://localhost:7000/logout', {
        'method': 'POST',
        'credentials': 'include', //specifies that when you receive cookies you should include them in requests
    }).then((response) => {
        if(response.status === 200){
            window.location.href = '/index.html';
        }
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
        return fetch(`http://localhost:7000/user/${user.uID}/reimbursement`, {
        'method': 'GET',
        'credentials': 'include'
        });
    }).then((response) => {
       return response.json()
    }).then((reimbursements) =>{
        populateReimbursements(reimbursements)
    })
}

function populateReimbursements(reimArray){
    //let reimSection = document.getElementById('reimbursements')
    let tbody = document.querySelector('#reimbursements tbody'); 

    for (const reim of reimArray){

        // console.log(reim);

       let tr = document.createElement('tr');
       
       let reimID = document.createElement('td');
       reimID.innerHTML = reim.rID;

       let reimType = document.createElement('td');
       reimType.innerHTML = reim.rType.rType;

       let reimDate = document.createElement('td');
       reimDate.innerHTML = reim.rSub;

       let reimAmount = document.createElement('td');
       reimAmount.innerHTML = reim.rAmount;

       let reimStatus = document.createElement('td');
       reimStatus.innerHTML = reim.rStatus.rStatus;

       let reimDesc = document.createElement('td');
       reimDesc.innerHTML = reim.desc;

        tr.appendChild(reimID);
        tr.appendChild(reimType);
        tr.appendChild(reimDate);
        tr.appendChild(reimAmount);
        tr.appendChild(reimStatus);
        tr.appendChild(reimDesc);

        tbody.appendChild(tr);
    }
}

logoutButton.addEventListener('click', logout);

window.addEventListener('load', onLoad);

submitButton.addEventListener('click', submit);