export class Dictionary {
    constructor(
        public welcome: string,
        public step: string,
        public options: string,
        public newLog: string,
        public nameOfProject: string,
        public history: string,
        public no_history: string
    ){}
}

const i18n: Map<string, Dictionary> = new Map();

export default i18n.set('de', {
    welcome: 'Willkommen',
    step: 'Schritt',
    options: 'Optionen',
    newLog: 'neues Log',
    nameOfProject: 'Name des Projekts',
    history: 'Verlauf',
    no_history: 'Es liegt noch kein Verlauf vor'
})
.set('en', {
    welcome: 'welcome',
    step: 'step',
    options: 'options',
    newLog: 'new log',
    nameOfProject: 'name of project',
    history: 'history',
    no_history: 'there is not yet a history'
})
.set('fr', {
    welcome: 'bienvenue',
    step: 'étape',
    options: 'options',
    newLog: 'nouveau journal',
    nameOfProject: 'nom du projet',
    history: 'cours',
    no_history: "Il n'y a pas encore d'histoire"
});