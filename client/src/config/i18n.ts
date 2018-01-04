export class Dictionary {
    constructor(
        public welcome: string,
        public step: string,
        public options: string,
        public newLog: string,
        public nameOfProject: string
    ){}
}

const i18n: Map<string, Dictionary> = new Map();

i18n.set('de', {
    welcome: 'Willkommen',
    step: 'Schritt',
    options: 'Optionen',
    newLog: 'neues Log',
    nameOfProject: 'Name des Projekts'
});

i18n.set('en', {
    welcome: 'welcome',
    step: 'step',
    options: 'options',
    newLog: 'new log',
    nameOfProject: 'name of project'
});

export default i18n;