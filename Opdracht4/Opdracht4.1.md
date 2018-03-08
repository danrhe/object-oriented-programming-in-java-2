a: De klasse MuisLuisteraar is een subklasse van MouseAdapter. 
Voor elke Swing Listener interface, die meerdere methoden specificeert bestaat ook een abstracte adapter klasse, die de interface implementeert. 
Door overerving worden ook de interfaces overgeerfd dus, dmv implementatie van de MouseAdapter-klasse wordt dus ook de 
MouseListener interface geimplementeerd.
   
b: De klasse PlaatsPanel van het componententype JPanel krijgt via de ZaalPanel constructor de MuisLuisteraar mee en deze wordt dan aan elke plaatspanel gekoppeld.

c: Een binnen-klasse kan alleen binnen een klasse worden gebruikt. Dat betekent dat een instantie van die binnenklasse ook niet verdergegeven kan worden.
Dit is handig als je de binnen-klasse maar op een plek nodig hebt. In dit geval echter kan de Muisluisteraar voor elke plaats-panel 
worden gebruikt. Dat zou wel betekenen dat we voor elke instantie van PlaatsPanel ook een nieuwe binnenklasse 
MuisLuisteraar zouden moeten definieren en instantieeren. Dit is niet efficient. In het ontwerp van deze opdracht is dus 
voor de eenmalige definitie en instantiering van het deze Luisteraar-klasse gekozen. En die wordt aan elke nieuwe instantie van PlaatsPanel meegegeven.
    