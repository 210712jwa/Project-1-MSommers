

let loginButton = document.getElementById('login');

let usernameInput = document.getElementById('username');
let passwordInput = document.getElementById('password');


const login = (event) => {
    event.preventDefault(); //prevents default behavior

    const loginInfo = {
        'username': usernameInput.value,
        'password': passwordInput.value
    };

    fetch('http://localhost:7000/login', {
        'method': 'POST',
        'credentials': 'include', //specifies that when you receive cookies you should include them in requests
        'headers': {
            'Content-Type': 'application/json' //application/json is a mime type
        },
        'body': JSON.stringify(loginInfo)
    }).then((response) => {
        if(response.status === 200){


            return response.json();
         
         
        } else if (response.status === 400){
            displayInvalidLogin();
        }
    }).then((user) => {
        
            if(user.userRole.uID == 2){
                window.location.href = '/admin.html'
            } else{
                window.location.href = '/employee.html'
            };
        })
};

function checkIfUserCurrentlyLoggedIn(event){
    fetch('http://localhost:7000/currentuser', {
        'method': 'GET',
        'credentials': 'include',
    }).then((response) => {
        if(response.status == 200){

            return response.json();
         
         
        } 
    }).then((user) => {
        
            if(user.userRole.uID == 2){
                window.location.href = '/admin.html'
            } else{
                window.location.href = '/employee.html'
            };

            })
        };
            

function displayInvalidLogin(){
    let loginForm = document.getElementById('loginform');

    let p = document.createElement('p');
    p.style.color = 'red';
    p.innerHTML = 'INVALID LOGIN';

    loginForm.appendChild(p);
}

loginButton.addEventListener('click', login);
window.addEventListener('load', checkIfUserCurrentlyLoggedIn);
