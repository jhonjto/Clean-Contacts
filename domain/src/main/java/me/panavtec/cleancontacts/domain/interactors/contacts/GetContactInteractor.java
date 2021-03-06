package me.panavtec.cleancontacts.domain.interactors.contacts;

import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.domain.model.ContactsLocalGateway;
import me.panavtec.cleancontacts.domain.model.LocalException;

public class GetContactInteractor implements Interactor<InteractorResponse<Contact>> {

  private ContactsLocalGateway localGateway;
  private String contactMd5;

  public GetContactInteractor(ContactsLocalGateway localGateway) {
    this.localGateway = localGateway;
  }

  public void setData(String contactMd5) {
    this.contactMd5 = contactMd5;
  }

  @Override public InteractorResponse<Contact> call() {
    try {
      return new InteractorResponse<>(localGateway.obtain(contactMd5));
    } catch (LocalException e) {
      return new InteractorResponse<>(new GetContactError());
    }
  }
}
