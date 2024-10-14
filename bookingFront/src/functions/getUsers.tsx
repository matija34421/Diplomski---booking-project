
async function getUsers(){
    const jwt = localStorage.getItem('jwt');
    if(jwt){
        fetch('http://localhost:8080/api/users',{
        method:'GET',
        headers:{'Content-Type':'application/json','Authorization':jwt}
    })
    .then(res => {return res.json();})
    .then((data) => { console.log(data) })
    .catch(err => {console.log(err.message)});
    }
}

export default getUsers;