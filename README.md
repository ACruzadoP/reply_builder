If you have ever worked as a Player Support Agent, I'm pretty sure you have noticed that all the prefabs tend to be stored in the same spreadsheet. This presents a few inconveniences that cause the list to never stop increasing. The most important inconveniences are:<br /><br />
&nbsp;&nbsp;&nbsp;&nbsp;- It takes too long for the Team Leads to go through all the prefabs in order to remove the unnecesary ones.<br />
&nbsp;&nbsp;&nbsp;&nbsp;- It's very likely to end up entering duplicate as well as unnecesary prefabs.<br /><br />
With that said, at certain point the PS agents have to spend long time in order to find the prefab that works better for a specific situation. When I realized about this issue, I thought it would be a good idea to create an Index of prefabs. As soon as I had an Index, I developed two Google Scripts that allowed me to get to the desired prefab as soon as possible.<br />
https://github.com/ACruzadoP/ps_prefabs_index

However, I still had to read and fix the prefabs before sending them in order to make sure they were working for every situation, and that was time consuming. At the time I only had two options:<br /><br />
&nbsp;&nbsp;&nbsp;&nbsp;- Use prefabs.<br />
&nbsp;&nbsp;&nbsp;&nbsp;- Write every reply from scratch.<br /><br />
After having developed this app, a third option started being available:<br /><br />
&nbsp;&nbsp;&nbsp;&nbsp;- Write every reply from scratch, using prefabs as well as prewritten sentences.<br /><br />
Before launching for the first time, you will have to delete the content of "log.txt". Also, the Spreadsheet you will be loading with the app has to be written on a certain way. In order to make it easier for you, I have included a template in the project's folder. Feel free to grab it and work from there.<br /><br />
Things you will have to keep in mind when creating your own Spreadhsheet:<br /><br />
&nbsp;&nbsp;&nbsp;&nbsp;- In order to create a parent folder, the cell where you write its name has to be italicised and placed on the column <code>A</code>.<br />
&nbsp;&nbsp;&nbsp;&nbsp;- In order to create child folders, you will have to list them on a the row next to the one you previously used in order to create their parent folder. This list has to be added to the column <code>B</code>. On the column <code>A</code>, you will have to add the word <code>Subfolders</code> and it has to be italicised as well.<br />
&nbsp;&nbsp;&nbsp;&nbsp;- In order to include a prefab on a parent folder, it has to be written below the row where you created it and above the row where you created the next parent folder.<br />
&nbsp;&nbsp;&nbsp;&nbsp;- In order to include a prefab on a child folder, it has to be written below the row where you created the child folders belonging to its parent, and above the row where you created the next parent folder. As a side note, you will have to write the child folder it belongs to on the column <code>D</code>.<br />
