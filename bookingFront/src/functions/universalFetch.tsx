async function universalFetch(
    url: string,
    HttpMethod: string,
    jwt: string,
    object?: Object
  ): Promise<any> {
    try {
      if (HttpMethod === 'GET' || HttpMethod === 'DELETE') {
        const response = await fetch(url, {
          method: HttpMethod,
          headers: {
            'Authorization': 'Bearer ' + jwt,
            'Content-Type': 'application/json',
          },
        });
  
        if (!response.ok) {
          throw new Error('Error: ' + response.status);
        }
        
        if(HttpMethod=="DELETE"){
          return null;
        }
        const data = await response.json();
        console.log(data);
        return data;
      } 
      
      else if (HttpMethod === "POST" || HttpMethod === "PUT") {
        const response = await fetch(url, {
          method: HttpMethod,
          headers: {
            'Authorization': "Bearer " + jwt,
            "Content-Type": "application/json",
          },
          body: JSON.stringify(object),
        });
  
        if (!response.ok) {
          throw new Error("Error: " + response.status);
        }
  
        const data = await response.json();
        return data; 
      }
    } catch (err) {
      console.error("Fetch error:", err);
      return null; 
    }
  }
  
  export default universalFetch;