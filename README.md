# Sensor
 <table>
  <tr>
    <th>Method</th>
    <th>Request</th>
    <th>Response</th>
    <th>Body</th>
  </tr>
  
   <tr>
    <td>POST</td>
    <td><code>/sensors/registration</code></td>
    <td align="center">200</td>
    <td><pre><code>
{
    "name": "Sensor1" </br>
}
    </code></pre></td>
  </tr>
  
  <tr>
   <td>POST</td>
    <td><code>/measurements/add</code></td>
    <td align="center">200</td>
    <td><pre><code>
{
    "value": 12.34,
    "raining": true,
    "sensor": {
        "name": "Sensor1"
    }
}
    </code></pre></td>
  </tr>
  
  <tr>
   <td>GET</td>
    <td><code>/measurements</code></td>
    <td align="center">200</td>
    <td><pre><code>
{
    "measurements": [
        {
            "value": 18.46,
            "raining": false,
            "sensor": {
                "name": "Sensor1"         
            }
        },
                {
            "value": 16.27,
            "raining": true,
            "sensor": {
                "name": "Sensor1"
            }
        }
    ]
}
   </code></pre></td>
  </tr>
    
   <tr>
    <td>GET</td>
    <td><code>/measurements/rainyDaysCount</code></td>
    <td align="center">200</td>
    <td><pre><code>
{
    "rainyDays": 41
}
   </code></pre></td>
  </tr>

</table>
 
\
&nbsp;
\
&nbsp;







                                                                                                     
                                                                                                     
