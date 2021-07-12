describe('Personal website home page', () => {
    
    beforeEach(() => {
        cy.visit('https://8081-blue-otter-wkw3yo67.ws-us11.gitpod.io/')
    })

    it('contains "Saloon - Organize seus salões" in the title', () => {
        cy.title().should('contain', 'Saloon - Organize seus salões')
    })
  })