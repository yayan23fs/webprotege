package edu.stanford.bmir.protege.web.server.revision;

import edu.stanford.bmir.protege.web.server.dispatch.ExecutionContext;
import edu.stanford.bmir.protege.web.server.owlapi.OWLAPIProject;
import edu.stanford.bmir.protege.web.server.owlapi.OWLAPIProjectManager;
import edu.stanford.bmir.protege.web.server.owlapi.change.OWLAPIChangeManager;
import edu.stanford.bmir.protege.web.shared.revision.GetRevisionSummariesAction;
import edu.stanford.bmir.protege.web.shared.revision.GetRevisionSummariesResult;
import edu.stanford.bmir.protege.web.shared.revision.RevisionSummary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 21/02/15
 */
@RunWith(MockitoJUnitRunner.class)
public class GetRevisionSummariesActionHandler_TestCase {

    private GetRevisionSummariesActionHandler handler;

    @Mock
    private GetRevisionSummariesAction action;

    @Mock
    private OWLAPIProject project;

    @Mock
    private ExecutionContext executionContext;

    @Mock
    private OWLAPIChangeManager changeManager;

    private List<RevisionSummary> revisionSummaries;

    @Mock
    private RevisionSummary summaryA, summaryB, summaryC;

    @Mock
    private OWLAPIProjectManager projectManager;

    @Before
    public void setUp() throws Exception {
        revisionSummaries = new ArrayList<>();
        revisionSummaries.add(summaryA);
        revisionSummaries.add(summaryB);
        revisionSummaries.add(summaryC);
        handler = new GetRevisionSummariesActionHandler(projectManager);
        when(project.getChangeManager()).thenReturn(changeManager);
        when(changeManager.getRevisionSummaries()).thenReturn(revisionSummaries);
    }

    @Test
    public void shouldReturnRevisionSummaries() {
        GetRevisionSummariesResult result = handler.execute(action, project, executionContext);
        assertThat(result.getRevisionSummaries(), contains(summaryA, summaryB, summaryC));
    }
}
