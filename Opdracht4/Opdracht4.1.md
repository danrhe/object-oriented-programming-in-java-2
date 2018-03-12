a: De klasse MuisLuisteraar is een subklasse van MouseAdapter. 
Voor elke Swing Listener interface, die meerdere methoden specificeert bestaat ook een abstracte adapter klasse, die de interface implementeert. 
Omdat door overerving ook interfaces over worden geerft, en de MuisLuisterar overerft van MouseAdapter, implementeer de MuisLuisteraar-klasse dus ook de 
MouseListener interface.
   
b: De klasse PlaatsPanel van het componententype JPanel krijgt van de VoorstellingsPanel klasse via de ZaalPanel constructor de MuisLuisteraar mee en deze wordt dan tijdens constructie aan elke instantie van de klasse PlaatsPanel gekoppeld.

c: Een binnen-klasse is een klasse die gedefineerd is binnen een klasse en ook alleen binnen de scope van de buitenklasse kan worden gebruikt.  
Dit is handig als je de binnen-klasse maar op een plek nodig hebt. In deze opdracht echter moet de Muisluisteraar voor elke PlaatsPanel instantie
worden gebruikt. Stel je definieert de MuisLuisteraar binnen PlaatsPanel, dan zou dat betekenen dat we voor elke instantie van PlaatsPanel ook een nieuwe binnenklasse 
MuisLuisteraar zouden moeten instantieeren. Dit is niet efficient. In het ontwerp van deze opdracht is dus 
voor de eenmalige definitie en instantiering van het deze Luisteraar-klasse buiten de PlaatsPanel klasse gekozen. En die wordt telkens aan elke nieuwe instantie van PlaatsPanel meegegeven.
    