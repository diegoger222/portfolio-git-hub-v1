import { createTreeWithEmptyWorkspace } from '@nx/devkit/testing';
import { Tree, readProjectConfiguration } from '@nx/devkit';

import { tsBackendModuleGenerator } from './ts-backend-module';
import { TsBackendModuleGeneratorSchema } from './schema';

describe('ts-backend-module generator', () => {
  let tree: Tree;
  const options: TsBackendModuleGeneratorSchema = { name: 'test' };

  beforeEach(() => {
    tree = createTreeWithEmptyWorkspace();
  });

  it('should run successfully', async () => {
    await tsBackendModuleGenerator(tree, options);
    const config = readProjectConfiguration(tree, 'test');
    expect(config).toBeDefined();
  });
});
